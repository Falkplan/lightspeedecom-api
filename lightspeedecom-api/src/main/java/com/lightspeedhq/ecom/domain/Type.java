package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.io.Serializable;
import lombok.Getter;

/**
 * Retrieve all the types from this shop. Types are referred to as Specifications in the SEOshop backoffice,
 * these are used interchangeably. Specifications provide an overview of certain product features.
 * For example a specification could be a Phone with the attributes like Camera, Color, and Dimensions.
 *
 * @see <a href="http://developers.seoshop.com/api/resources/type">http://developers.seoshop.com/api/resources/type</a>
 * @author stevensnoeijen
 */
@JsonIgnoreProperties({"attributes"})
@JsonRootName("type")
public class Type implements Serializable {

    private static final long serialVersionUID = 2L;

    /**
     * The unique numeric identifier for the type.<br/>
     * Example: <code>{"id": "{id}"}</code>
     */
    @Getter
    private int id;

    /**
     * The title of the specification.<br/>
     * Example: <code>{"title": "GroPro"}</code>
     */
    @Getter
    private String title;
}
