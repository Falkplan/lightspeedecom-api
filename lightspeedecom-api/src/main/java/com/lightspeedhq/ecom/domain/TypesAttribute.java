package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import java.util.ArrayList;
import lombok.Getter;
import com.lightspeedhq.ecom.jackson.ResourceIdDeserializer;

/**
 * Attributes belong to a type, also referred to as a specification. An attribute could be for example the Dimensions or Color of a product.
 * {@link com.lightspeedhq.ecom.LightspeedEComClient#getType}
 *
 * @see <a href="http://developers.seoshop.com/api/resources/typesattribute">http://developers.seoshop.com/api/resources/typesattribute</a>
 * @author stevensnoeijen
 */
@JsonRootName("typeAttribute")
public class TypesAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonRootName("typesAttributes")
    public static class List extends ArrayList<TypesAttribute> {
    }

    /**
     * The unique numeric identifier for the typesAttribute.<br/>
     * Example: <code>{"id": "{id}"}</code>
     */
    @Getter
    private int id;

    /**
     * The position in the list of attributes.<br/>
     * Example: <code>{"sortOrder":2}</code>
     */
    @Getter
    private int sortOrder;

    /**
     * Link to Types resource.<br/>
     * Use this value get the {@link Type} with {@link com.lightspeedhq.ecom.LightspeedEComClient#getType(int)}
     */
    @JsonProperty("type")
    @JsonDeserialize(using = ResourceIdDeserializer.class)
    @Getter
    private int typeId;

    /**
     * Link to Attributes resource.
     * Use to get {@link Attribute} with {@link com.lightspeedhq.ecom.LightspeedEComClient#getAttribute(int) }
     */
    @JsonProperty("attribute")
    @JsonDeserialize(using = ResourceIdDeserializer.class)
    @Getter
    private int attributeId;
}
