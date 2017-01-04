package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.lightspeedhq.ecom.LightspeedEComClient;
import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.Getter;

/**
 * The suppliers from the shop.
 *
 * @see <a href="http://developers.seoshop.com/api/resources/supplier">http://developers.seoshop.com/api/resources/supplier</a>
 * @author stevensnoeijen
 */
@JsonRootName("supplier")
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    private int id;

    /**
     * The date and time when the supplier was created.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"createdAt": "2013-09-24T21:48:17+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime createdAt;

    /**
     * The date and time when the supplier was last updated.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"createdAt": "2013-09-24T21:48:17+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime updatedAt;

    /**
     * Name of the supplier.<br/>
     * Example: <code>{"title": "Siwy Jeans"}</code>
     */
    @Getter
    private String title;

    @Getter
    @JsonProperty("attention_of")
    private String attentionOf;

    @Getter
    private String street;

    @Getter
    private String street2;

    @Getter
    private String number;

    @Getter
    private String extension;

    @Getter
    @JsonProperty("zip_code")
    private String zipCode;

    @Getter
    private String city;

    @Getter
    private String region;

    @Getter
    @JsonProperty("country_id")
    private Country country;
}
