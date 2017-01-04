package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.lightspeedhq.ecom.LightspeedEComClient;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import lombok.Getter;

/**
 * Retrieve the brands from the shop.
 *
 * @see <a href="http://developers.seoshop.com/api/resources/brand">http://developers.seoshop.com/api/resources/brand</a>
 * @author stevensnoeijen
 */
@JsonIgnoreProperties({"products"})
@JsonRootName("brand")
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonRootName("brands")
    public static class List extends ArrayList<Brand> {

        public Brand findById(int id) {
            return stream().filter(brand -> brand.id == id).findFirst().orElse(null);
        }
    }

    /**
     * The unique numeric identifier for the brand.<br/>
     * Example: <code>{"id": "{id}"}</code>
     */
    @Getter
    private int id;

    /**
     * The date and time when the brand was created.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"createdAt": "2013-09-24T21:48:17+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime createdAt;

    /**
     * The date and time when the brand was last updated.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"updatedAt": "2013-09-24T22:02:24+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime updatedAt;

    /**
     * The url for this brand, usually a slug version of the title.<br/>
     * Example: <code>{"url": "apple-inc"}</code>
     */
    @Getter
    private String url;

    /**
     * The brand name.<br/>
     * Example: <code>{"title": "Apple Inc."}</code>
     */
    @Getter
    private String title;

    /**
     * A summary or description of this brand.<br/>
     * Example: <code>{"content": "Apple Inc. is an American technology company."}</code>
     */
    @Getter
    private String content;

    /**
     * An image or brand logo. See <a href="http://developers.seoshop.com/api/resources/brandimage">Image</a>
     * Id will be empty inside the brand.
     */
    @Getter
    private Image image;

    @Getter
    @JsonProperty("isVisible")
    private boolean isVisible;
}
