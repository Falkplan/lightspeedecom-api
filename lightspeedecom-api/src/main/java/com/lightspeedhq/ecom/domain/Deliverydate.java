package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.lightspeedhq.ecom.LightspeedEComClient;
import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.Getter;

/**
 * The delivery dates from the shop.
 *
 * @see <a href="http://developers.seoshop.com/api/resources/deliverydate">http://developers.seoshop.com/api/resources/deliverydate</a>
 * @author stevensnoeijen
 */
@JsonRootName(value = "deliverydate")
public class Deliverydate implements Serializable {

    private static final long serialVersionUID = 2L;

    /**
     * The unique numeric identifier for the deliverydate.<br/>
     * Example: <code>{"id": "{id}"}</code>
     */
    @Getter
    private int id;

    /**
     * The date and time when the deliverydate was created.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"createdAt": "2013-09-24T21:48:17+02:00"}</code>
     */
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    private ZonedDateTime createdAt;

    /**
     * The date and time when the deliverydate was last updated.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"updatedAt": "2013-09-24T22:02:24+02:00"}</code>
     */
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    private ZonedDateTime updatedAt;

    /**
     * Property description.<br/>
     * Example: <code>{"name": "Default delivery date"}</code>
     */
    @Getter
    private String name;

    /**
     * The message to display when the product is in stock.<br/>
     * Example: <code>{"inStockMessage": "In stock and delivered in 24 hours"}</code>
     */
    @Getter
    private String inStockMessage;

    /**
     * The message to display when the product is out of stock.<br/>
     * Example: <code>{"outStockMessage": "Out of stock. Delivered in 5 days."}</code>
     */
    @Getter
    private String outStockMessage;
}
