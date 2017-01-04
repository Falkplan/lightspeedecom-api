package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.lightspeedhq.ecom.LightspeedEComClient;
import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.Getter;

/**
 * Create a CustomerToken to automaticly log customers in.
 * With this endpoint it is possible to create your own single sign on functionality.<br>
 * The token is valid for <u>one-time</u> use!
 *
 * @see <a href="http://developers.lightspeedhq.com/ecom/endpoints/singlesignon/">http://developers.lightspeedhq.com/ecom/endpoints/singlesignon/</a>
 * @author stevensnoeijen
 */
@JsonRootName("customerToken")
public class CustomerToken implements Serializable {

    private static final long serialVersionUID = 2L;

    public static final int EXPIRESIN_DEFAULT = 60;

    public static final int EXPIRESIN_MAX = 600;

    /**
     * The unique numeric identifier for the CustomerToken.<br>
     * Format: <code>{“id”: “{id}”}</code>
     */
    @Getter
    private int id;

    /**
     * The URL that is used to redirect the customer.<br>
     * Example: <code>{“redirectUrl”:http://www.domain.com/account/token/?token=a42c103f109038d8a00923494288a1e112b7001f}</code>
     */
    @Getter
    private String redirectUrl;

    /**
     * The URL to which the customer should be redirected - default is the homepage.<br>
     * Example: <code>{“returnUrl”: “http://www.domain.com/”}</code>
     */
    @Getter
    private String returnUrl;

    /**
     * The time in seconds that the URL will expire - default 60 seconds, max 3600.<br>
     * Example: <code>{“expiresIn”: “60”}</code>
     */
    @Getter
    private String expiresIn;

    /**
     * The date and time when the token was created.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"createdAt": "2013-09-24T21:48:17+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime createdAt;

    /**
     * The date and time when the token was last updated.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"createdAt": "2013-09-24T21:48:17+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime updatedAt;

    /**
     * Sets the required fields to request a customer token by the api.
     *
     * @param returnUrl The URL to which the customer should be redirected - default is the homepage
     * @param expiresIn The time in seconds that the URL will expire - default 60 seconds ({@link #EXPIRESIN_DEFAULT}), max 600 ({@link #EXPIRESIN_MAX}).
     */
    public static CustomerToken create(String returnUrl, int expiresIn) {
        CustomerToken customerToken = new CustomerToken();
        customerToken.returnUrl = returnUrl;
        if (expiresIn < 0 || expiresIn > EXPIRESIN_MAX) {
            throw new IllegalArgumentException("expiresIn must be within 0 and " + EXPIRESIN_MAX);
        }
        customerToken.expiresIn = String.valueOf(expiresIn);
        return customerToken;
    }
}
