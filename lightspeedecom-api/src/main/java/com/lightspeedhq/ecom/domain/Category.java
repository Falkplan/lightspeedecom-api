package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightspeedhq.ecom.LightspeedEComClient;
import com.lightspeedhq.ecom.jackson.FalseNullDeserializer;
import com.lightspeedhq.ecom.jackson.ResourceIdDeserializer;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import lombok.Getter;

/**
 * Manage the categories of a shop.
 *
 * @see <a href="http://developers.seoshop.com/api/resources/category">http://developers.seoshop.com/api/resources/category</a>
 * @author stevensnoeijen
 */
@JsonRootName("category")
public class Category implements Serializable {

    private static final long serialVersionUID = 2L;

    @JsonRootName("categories")
    public static class List extends ArrayList<Category> {

        public Category findById(int id) {
            return this.stream()
                    .filter(c -> c.id == id)
                    .findFirst()
                    .orElse(null);
        }
    }

    /**
     * Type of the category page in the frontend.
     */
    public static enum Type {
        INDEX, TEXTPAGE, CATEGORY;

        @JsonValue
        public String toJson() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static Gender fromJson(String value) {
            return Enum.valueOf(Gender.class, value.toUpperCase());
        }
    }

    /**
     * The unique numeric identifier for the category.<br/>
     * Format: <code>{"id": "{id}"}</code>
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
     * Is the category visible?<br>
     * Format: <code>{"isVisible": "false|true"}</code
     */
    @Getter
    @JsonProperty("isVisible")
    private boolean isVisible;

    /**
     * The depth of the category based on its parents.<br>
     * Example: <code>{"depth":1}</code>
     */
    @Getter
    private int depth;

    /**
     * Type of the category page in the frontend.<br>
     * Format: <code>{"type": "index|textpage|category"}</code>
     */
    @Getter
    private Type type;

    /**
     * Position of the category in the list.<br>
     * Example: <code>{"sortOrder":18}</code>
     */
    @Getter
    private int sortOrder;

    /**
     * The url that refers to the category, usually a slug version of the title.<br>
     * Example: <code>{"url": "computers-and-tablets"}</code>
     */
    @Getter
    private String url;

    /**
     * Title of the category.<br>
     * Example: <code>{"title": "Computers and tablets"}</code>
     */
    @Getter
    private String title;

    /**
     * Long title of the category.<br>
     * Example: <code>{"fulltitle": "Computers and tablets"}</code>
     */
    @Getter
    private String fulltitle;

    /**
     * A description of the contents of the category.<br>
     * Example: <code>{"description": "All computers and tables"}</code>
     */
    @Getter
    private String description;

    /**
     * A description of the contents of the category.<br>
     * Example: <code>{"content": "All computers and tables"}</code>
     */
    @Getter
    private String content;

    /**
     * An image for this category.
     */
    @Getter
    @JsonDeserialize(using = FalseNullDeserializer.class)
    private Image image;

    /**
     * Parent category of the current category.
     */
    @Getter
    @JsonProperty("parent")
    @JsonDeserialize(using = ResourceIdDeserializer.class)
    private int parentId;
}
