package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightspeedhq.ecom.jackson.ResourceIdDeserializer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;
import lombok.Getter;

/**
 * This resource manages the relationship between the categories and the products.
 *
 * @see <a href="http://developers.seoshop.com/api/resources/categoriesproduct">http://developers.seoshop.com/api/resources/categoriesproduct</a>
 * @author stevensnoeijen
 */
@JsonRootName("categoriesProduct")
public class CategoriesProduct implements Serializable {

    private static final long serialVersionUID = 2L;

    @JsonRootName("categoriesProducts")
    public static class List extends ArrayList<CategoriesProduct> {

        public java.util.List<CategoriesProduct> findByCategoryId(int categoryId) {
            return this.stream()
                    .filter(cp -> cp.getCategoryId() == categoryId)
                    .collect(Collectors.toList());
        }

        public java.util.List<CategoriesProduct> findByProductId(int productId) {
            return this.stream()
                    .filter(cp -> cp.getProductId() == productId)
                    .collect(Collectors.toList());
        }
    }

    /**
     * The unique numeric identifier for the categoriesProduct.<br>
     * Format: <code>{"id": "{id}"}</code>
     */
    @Getter
    private int id;

    /**
     * Position within the list.<br>
     * Example: <code>{"sortOrder":0}</code>
     */
    @Getter
    private int sortOrder;

    /**
     * Link to Categories resource.
     *
     * @see Category
     */
    @Getter
    @JsonProperty("category")
    @JsonDeserialize(using = ResourceIdDeserializer.class)
    private int categoryId;

    /**
     * Link to Products resource
     *
     * @see Product
     */
    @Getter
    @JsonProperty("product")
    @JsonDeserialize(using = ResourceIdDeserializer.class)
    private int productId;
}
