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
 * Products are the primary items for sale in a shop. A product always has one or more variants and zero or more images.
 *
 * @see <a href="http://developers.seoshop.com/api/resources/product">http://developers.seoshop.com/api/resources/product</a>
 * @author stevensnoeijen
 */
@JsonRootName("product")
public class Product implements Serializable {

    private static final long serialVersionUID = 2L;

    @JsonRootName("products")
    public static class List extends ArrayList<Product> {
    }

    public enum Visibility {
        HIDDEN, VISIBLE, AUTO;

        @JsonValue
        public String toJson() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static Visibility fromJson(String value) {
            return Enum.valueOf(Visibility.class, value.toUpperCase());
        }
    }

    /**
     * The unique numeric identifier for the product.<br/>
     * Example: <code>{"id": "{id}"}</code>
     */
    @Getter
    private int id;

    /**
     * The date and time when the product was created.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"createdAt": "2013-09-24T21:48:17+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime createdAt;

    /**
     * The date and time when the product was last updated.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"updatedAt": "2013-09-24T22:02:24+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime updatedAt;

    /**
     * Product visibility.<br/>
     * Example: <code>{"isVisible": "true|false"}</code>
     */
    @JsonProperty("isVisible")
    @Getter
    private boolean isVisible;

    /**
     * Product visibility setting.<br/>
     * Example: <code>{"visibility": "hidden, visible, auto"}</code>
     */
    @Getter
    private Visibility visibility;

    @Getter
    private boolean hasMatrix;

    /**
     * Extra template variable data01.<br/>
     * Example: <code>{"data01": ""}</code>
     */
    @Getter
    private String data01;

    /**
     * Extra template variable data02.<br/>
     * Example: <code>{"data02": ""}</code>
     */
    @Getter
    private String data02;

    /**
     * Extra template variable data03.<br/>
     * Example: <code>{"data03": ""}</code>
     */
    @Getter
    private String data03;

    /**
     * Product slug.<br/>
     * Example: <code>{"url": "wade-crewneck-navyblue"}</code>
     */
    @Getter
    private String url;

    /**
     * Product title.<br/>
     * Example: <code>{"title": "Wade Crewneck Navyblue"}</code>
     */
    @Getter
    private String title;

    /**
     * Product full title.<br/>
     * Example: <code>{"fulltitle": "Wade Crewneck Navyblue"}</code>
     */
    @Getter
    private String fulltitle;

    /**
     * Product description.<br/>
     * Example: <code>{"description": "De Wade Crewneck Navyblue van Wemoto. Deze blauwe trui met de enorme W op de chest heeft een heel klassieke look."}</code>
     */
    @Getter
    private String description;

    /**
     * Product content.<br/>
     * Example: <code>{"content": "De Wade Crewneck Navyblue van Wemoto. Deze blauwe trui met de enorme W op de chest heeft een heel klassieke look."}</code>
     */
    @Getter
    private String content;

    /**
     * Link to <a href="http://developers.seoshop.com/api/resources/brand">Brand</a> resource.<br/>
     * Use this value get the {@link Brand} with {@link com.lightspeedhq.ecom.LightspeedEComClient#getBrand(int) }.
     * Value is "-1" when no value is set.
     */
    @Getter
    @JsonProperty("brand")
    @JsonDeserialize(using = ResourceIdDeserializer.class)
    private int brandId;

    /**
     * Link to <a href="http://developers.seoshop.com/api/resources/deliverydate">DeliveryDate</a> resource.<br/>
     * Use this value get the {@link Deliverydate} with {@link com.lightspeedhq.ecom.LightspeedEComClient#getDeliverydate(int) }.
     * Value is "-1" when no value is set.
     */
    @Getter
    @JsonProperty("deliverydate")
    @JsonDeserialize(using = ResourceIdDeserializer.class)
    private int deliverydateId;

    /**
     * Main product image. See <a href="http://developers.seoshop.com/api/resources/file">File</a>
     * Id will be empty inside the product.
     */
    @Getter
    @JsonDeserialize(using = FalseNullDeserializer.class)
    private Image image;

    /**
     * Link to <a href="http://developers.seoshop.com/api/resources/type">Type</a> resource.<br/>
     * Use this value get the {@link Type} with {@link com.lightspeedhq.ecom.LightspeedEComClient#getType(int) }.
     */
    @Getter
    @JsonProperty("type")
    @JsonDeserialize(using = ResourceIdDeserializer.class)
    private int typeId;

    /**
     * Link to <a href="http://developers.seoshop.com/api/resources/type">Type</a> resource.<br/>
     * Use this value get the {@link Supplier} with {@link com.lightspeedhq.ecom.LightspeedEComClient#getSupplier(int) }.
     */
    @Getter
    @JsonProperty("supplier")
    @JsonDeserialize(using = ResourceIdDeserializer.class)
    private int supplierId;

}
