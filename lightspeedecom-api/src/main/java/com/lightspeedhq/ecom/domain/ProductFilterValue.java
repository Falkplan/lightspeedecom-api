package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightspeedhq.ecom.jackson.ResourceIdDeserializer;
import java.io.Serializable;
import java.util.ArrayList;
import lombok.Getter;

/**
 * ProductFilterValues are links between products, filters and their values.
 * You can use them to hook a value to a product.
 *
 * @see <a href="http://developers.seoshop.com/api/resources/productfiltervalue">http://developers.seoshop.com/api/resources/productfiltervalue</a>
 * @author stevensnoeijen
 */
@JsonRootName("productFilterValue")
public class ProductFilterValue implements Serializable {

    private static final long serialVersionUID = 2L;

    @JsonRootName("productFiltervalue")//should be productFiltervalues (probably a bug), may be fixed later...
    public static class List extends ArrayList<ProductFilterValue> {
    }

    /**
     * The unique numeric identifier for the productFilterValue.<br>
     * Format: <code>{"id": "{id}"}</code>
     */
    @Getter
    private int id;

    /**
     * Link to {@link Filter} resource.
     *
     * @see com.lightspeedhq.ecom.LightspeedEComClient#getFilter
     */
    @Getter
    @JsonProperty("filter")
    @JsonDeserialize(using = ResourceIdDeserializer.class)
    private int filterId;

    /**
     * Link to {@link FilterValue} resource.
     *
     * @see com.lightspeedhq.ecom.LightspeedEComClient#getFilterValue
     */
    @Getter
    @JsonProperty("filtervalue")
    @JsonDeserialize(using = ResourceIdDeserializer.class)
    private int filtervalueId;

}
