package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lightspeedhq.ecom.LightspeedEComClient;
import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.Getter;

/**
 * The image that belongs to a brand, product or category.
 *
 * @see <a href="http://developers.seoshop.com/api/resources/brandimage">http://developers.seoshop.com/api/resources/brandimage</a>
 * @see <a href="http://developers.seoshop.com/api/resources/productimage">http://developers.seoshop.com/api/resources/productimage</a>
 * @see <a href="http://developers.seoshop.com/api/resources/categoryimage">http://developers.seoshop.com/api/resources/categoryimage</a>
 * @author stevensnoeijen
 */
public class Image implements Serializable {

    private static final long serialVersionUID = 2L;

    /**
     * The unique numeric identifier.<br/>
     * Example: <code>{"id": "{id}"}</code>
     */
    @Getter
    private int id;

    /**
     * The date and time when the image was created.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"createdAt": "2013-09-24T21:48:17+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime createdAt;

    /**
     * The date and time when the image was last updated.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"updatedAt": "2013-09-24T22:02:24+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime updatedAt;

    /**
     * The extension of the image, this is usually JPG.<br/>
     * Example: <code>{"extension": "jpg"}</code>
     */
    @Getter
    private String extension;

    /**
     * The size of the image in bytes.<br/>
     * Example: <code>{"size":32431}</code>
     */
    @Getter
    private int size;

    /**
     * The title of the image.<br/>
     * Example: <code>{"title": "Lorum ipsum dolor sit amet"}</code>
     */
    @Getter
    private String title;

    /**
     * The thumbnail version of the image.<br/>
     * Example: <code>{"thumb": "http://static.webshopapp.com/shops/000001/files/000000001/50x50x2/2164699050-a4c5ff4686.jpg"}</code>
     */
    @Getter
    private String thumb;

    /**
     * The larger version of the image.<br/>
     * Example: <code>{"src": "http://static.webshopapp.com/shops/000001/files/000000001/2164699050-a4c5ff4686.jpg"}</code>
     */
    @Getter
    private String src;
}
