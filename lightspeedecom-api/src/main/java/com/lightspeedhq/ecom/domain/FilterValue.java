package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.lightspeedhq.ecom.LightspeedEComClient;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import lombok.Getter;

/**
 * FilterValues are used to describe products in the catalog so they can be filtered on the frontend.
 *
 * @see <a href="http://developers.seoshop.com/api/resources/filtervalue">http://developers.seoshop.com/api/resources/filtervalue</a>
 * @author stevensnoeijen
 */
@JsonRootName("filterValue")
public class FilterValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonRootName("filterValues")
    public static class List extends ArrayList<FilterValue> {
    }

    /**
     * The unique numeric identifier for the filterValue.<br>
     * Format: <code>{"id": "{id}"}</code>
     */
    @Getter
    private int id;

    /**
     * The date and time when the filterValue was created.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"createdAt": "2013-09-24T21:48:17+02:00"}</code>
     */
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    private ZonedDateTime createdAt;

    /**
     * The date and time when the filterValue was last updated.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"updatedAt": "2013-09-24T22:02:24+02:00"}</code>
     */
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    private ZonedDateTime updatedAt;

    /**
     * FilterValue title.<br>
     * Example: <code>{"title": "Color"}</code>
     */
    @Getter
    private String title;

    /**
     * This indicates how it should be sorted.<br>
     * Example: <code>{"position":9}</code>
     */
    @Getter
    private int position;
}
