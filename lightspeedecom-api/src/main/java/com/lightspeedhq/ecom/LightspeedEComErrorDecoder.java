package com.lightspeedhq.ecom;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lightspeedhq.ecom.domain.LightspeedEComError;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.util.Date;
import lombok.extern.java.Log;

/**
 *
 * @author stevensnoeijen
 */
@Log
public class LightspeedEComErrorDecoder implements ErrorDecoder {

    private final ObjectMapper om;
    /**
     * To update the limit,remaining and rest values
     */
    private final Limiter limiter;

    /**
     *
     * @param limiter may be null
     */
    public LightspeedEComErrorDecoder(Limiter limiter) {
        om = new ObjectMapper();
        om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        this.limiter = limiter;
    }

    public LightspeedEComErrorDecoder() {
        this(null);
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        if (limiter != null) {
            limiter.update(response);//updates limit-data from headers
        }
        if (response.status() >= 400 && response.status() < 600) {
            LightspeedEComError error;
            try {
                error = om.readValue(response.body().asInputStream(), LightspeedEComError.class);
            } catch (IOException ex) {
                return new RuntimeException("json serialization of lightspeedecom error", ex);
            }
            LightspeedEComErrorException exception = new LightspeedEComErrorException(response.reason(), error);
            if (response.status() == 429) {
                //if to many requests
                String resetString = (String) response.headers().get(LightspeedEComClient.HEADER_RATELIMIT_RESET).toArray()[0];
                int reset5min = Integer.parseInt(resetString.split("/")[0]);

                Date retryAfter = new Date(System.currentTimeMillis() + (reset5min * 1000));
                log.info("To many requests, wait " + (reset5min * 1000) + "ms");
                //activates retryer
                throw new RetryableException(error.getMessage(), exception, retryAfter);
            } else {
                return exception;
            }
        } else {
            return new ErrorDecoder.Default().decode(methodKey, response);
        }
    }

}
