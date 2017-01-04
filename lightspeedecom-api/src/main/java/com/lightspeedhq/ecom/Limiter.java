package com.lightspeedhq.ecom;

import com.google.common.collect.Iterables;
import feign.Response;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import lombok.extern.java.Log;

/**
 *
 * @author stevensnoeijen
 */
@Log
public class Limiter {

    private int limit5min;
    private int limit1h;
    private int limit1d;

    private int remaining5min;
    private int remaining1h;
    private int remaining1d;

    private int reset5min;
    private int reset1h;
    private int reset1d;

    private long lastUpdate;

    /**
     * number of requests that need to be remaining
     */
    private int minRemaining;

    /**
     * number of requests that may be used by this client
     */
    private int maxRequests;

    /**
     * to force request to be executed
     */
    private boolean force;

    private int doneRequests = 0;

    /**
     * @param minRemaining min number of requests that need not to be used (for more important usage?), by default 0
     * @param maxRequests max number of requests that may be used by this client within a reset
     * @param force to force request, if true request will be executed (thread will sleep), if false it will throw an exception
     */
    public Limiter(int minRemaining, int maxRequests, boolean force) {
        this.minRemaining = minRemaining;
        this.maxRequests = maxRequests;
        this.force = force;
    }

    public void update(Response response) {
        boolean isError = response.status() >= 400 && response.status() < 600;

        String limitString = Iterables.get(response.headers().get(LightspeedEComClient.HEADER_RATELIMIT_LIMIT), 0);
        String remainingString = Iterables.get(response.headers().get(LightspeedEComClient.HEADER_RATELIMIT_REMAINING), 0);
        String resetString = Iterables.get(response.headers().get(LightspeedEComClient.HEADER_RATELIMIT_RESET), 0);

        //limit
        String[] limitStrings = limitString.split("/");
        this.limit5min = Integer.parseInt(limitStrings[0]);
        this.limit1h = Integer.parseInt(limitStrings[1]);
        this.limit1d = Integer.parseInt(limitStrings[2]);

        //remaining
        String[] remainingStrings = remainingString.split("/");
        this.remaining5min = Integer.parseInt(remainingStrings[0]);
        this.remaining1h = Integer.parseInt(remainingStrings[1]);
        this.remaining1d = Integer.parseInt(remainingStrings[2]);

        //reset
        String[] resetStrings = resetString.split("/");
        this.reset5min = Integer.parseInt(resetStrings[0]);//5min
        this.reset1h = Integer.parseInt(resetStrings[1]);//1h
        this.reset1d = Integer.parseInt(resetStrings[2]);//1d

        //lastUpdate
        String dateString = Iterables.get(response.headers().get("Date"), 0);
        DateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z");
        Date date;
        try {
            date = format.parse(dateString);
        } catch (ParseException ex) {
            log.log(Level.WARNING, "Could not parse Date, using current date instead.", ex);
            date = new Date();
        }

        //check lastUpdate
        if (lastUpdate != 0) {
            //check if lastUpdate is 5min ago
            LocalDateTime resetTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(this.lastUpdate), TimeZone.getDefault().toZoneId());
            resetTime = resetTime.plus((int) 5 - (resetTime.getMinute() % 5), ChronoUnit.MINUTES).withSecond(0).withNano(0);//get reset time (is the next 5min)

            //after reset time
            if (LocalDateTime.now().isAfter(resetTime)) {
                this.doneRequests = 0;//reset to 0
            }
        }

        if (!isError) {
            this.lastUpdate = date.getTime();
            this.doneRequests++;
        }
    }

    /**
     * Checks if a request can be made
     */
    public void waitOrContinue() {
        if (this.lastUpdate == 0) {//first time
            return;//continue
        }
        //check if lastUpdate is 5min ago
        Date resetTime = new Date(this.lastUpdate + (reset5min * 1000));
        Date now = new Date();
        //after reset time
        if (now.after(resetTime)) {
            return;//continue
        }
        if (this.force) {
            //check minRemaining and maxRequests
            if (this.minRemaining > this.remaining5min - 1
                    || (this.doneRequests + 1) > this.maxRequests) {
                //remaining5min is to low and/or maxRequests is reached
                //calculate time to wait
                long wait = resetTime.getTime() - now.getTime() + 1000;//1sec slack
                try {
                    log.info("limit reached, wait for " + wait + "ms");
                    Thread.sleep(wait);
                    log.info("continue after " + wait + "ms sleep");
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            } else {
                return;//continue
            }
        } else {
            throw new LimitException();
        }
    }

}
