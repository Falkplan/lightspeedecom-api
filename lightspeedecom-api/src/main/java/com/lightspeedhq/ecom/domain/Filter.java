package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.lightspeedhq.ecom.LightspeedEComClient;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import lombok.Getter;

/**
 * Filters are used to filter the catalog on the frontend.
 *
 * @see <a href="http://developers.seoshop.com/api/resources/filter">http://developers.seoshop.com/api/resources/filter</a>
 * @author stevensnoeijen
 */
@JsonRootName("filter")
public class Filter implements Serializable {

    private static final long serialVersionUID = 2L;

    @JsonRootName("filters")
    public static class List extends ArrayList<Filter> {
    }

    /**
     * The unique numeric identifier for the filter.<br>
     * Format: <code>{"id": "{id}"}</code>
     */
    @Getter
    private int id;

    /**
     * The date and time when the filter was created.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"createdAt": "2013-09-24T21:48:17+02:00"}</code>
     */
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    private ZonedDateTime createdAt;

    /**
     * The date and time when the filter was last updated.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"updatedAt": "2013-09-24T22:02:24+02:00"}</code>
     */
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    private ZonedDateTime updatedAt;

    /**
     * Filter title.<br>
     * Example: <code>{"title": "Color"}</code>
     */
    @Getter
    private String title;

    /**
     * Activates the filter when set to true.<br>
     * Example: <code>{"isActive":true}</code>
     */
    @Getter
    @JsonProperty("isActive")
    private boolean isActive;

    /**
     * This indicates how it should be sorted.<br>
     * Example: <code>{"position":9}</code>
     */
    @Getter
    private int position;

}
