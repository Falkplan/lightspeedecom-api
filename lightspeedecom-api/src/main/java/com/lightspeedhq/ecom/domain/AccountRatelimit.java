package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.lightspeedhq.ecom.LightspeedEComClient;
import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.Getter;

/**
 * Retrieve the rate limits for the active API key used to perform the request.
 *
 * @see <a href="http://developers.seoshop.com/api/resources/accountratelimit">http://developers.seoshop.com/api/resources/accountratelimit</a>
 * @author stevensnoeijen
 */
@JsonRootName("accountRatelimit")
public class AccountRatelimit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Rate limit for a time unit.
     */
    public static class Ratelimit {

        /**
         * Limit of requests.
         */
        @Getter
        private int limit;

        /**
         * Requests remaining.
         */
        @Getter
        private int remaining;

        /**
         * Seconds remaining to reset.
         */
        @Getter
        private int reset;

        /**
         * The time when this rate limit will be reset.
         */
        @Getter
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
        private ZonedDateTime resetTime;
    }

    /**
     * The 5 minute rate limit.
     */
    @Getter
    private Ratelimit limit5Min;

    /**
     * The one hour rate limit.
     */
    @Getter
    private Ratelimit limitHour;

    /**
     * The 24 hour rate limit.
     */
    @Getter
    private Ratelimit limitDay;
}
