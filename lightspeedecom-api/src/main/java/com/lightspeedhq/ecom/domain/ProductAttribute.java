package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.io.Serializable;
import java.util.ArrayList;
import lombok.Getter;

/**
 * Attributes that belong to a product. A product may have multiple attributes.
 *
 * @see <a href="http://developers.seoshop.com/api/resources/productattribute">http://developers.seoshop.com/api/resources/productattribute</a>
 * @author stevensnoeijen
 */
@JsonRootName("productAttribute")
public class ProductAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonRootName("productAttributes")
    public static class List extends ArrayList<ProductAttribute> {
    }

    /**
     * The unique numeric identifier for the productAttribute.<br/>
     * Example: <code>{"id": "{id}"}</code>
     */
    @Getter
    private int id;

    /**
     * The value of the attribute.<br/>
     * Example: <code>{"value": "Value"}</code>
     */
    @Getter
    private String value;

    /**
     * The attribute. See Attribute.<br/>
     * Example: <code>{"attribute": "Attribute"}</code>
     */
    @Getter
    private Attribute attribute;
}
