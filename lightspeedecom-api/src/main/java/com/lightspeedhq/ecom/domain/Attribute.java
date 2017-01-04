package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.io.Serializable;
import lombok.Getter;

/**
 * Retrieve all attributes available in the shop.
 * Attributes can be attached to one or more specifications.
 * This may be used to describe the specifications of a product and compare two products based on the attributes.
 *
 * @see <a href="http://developers.seoshop.com/api/resources/attribute">http://developers.seoshop.com/api/resources/attribute</a>
 * @author stevensnoeijen
 */
@JsonIgnoreProperties({"types"})
@JsonRootName("attribute")
public class Attribute implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The unique numeric identifier for the attribute.<br/>
     * Example: <code>{"id": "{id}"}</code>
     */
    @Getter
    private int id;

    /**
     * Title of the attribute.<br/>
     * Example: <code>{"title": "Camera"}</code>
     */
    @Getter
    private String title;

    /**
     * Default value for the attribute. The actual value may be overwritten for each product.<br/>
     * Example: <code>{"defaultValue": "Nikon"}</code>
     */
    @Getter
    private String defaultValue;
}
