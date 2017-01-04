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
import org.apache.commons.lang.StringUtils;

/**
 * Variants are variations of the product.
 * Prices, stock, size, weight, articleCode, SKU and EAN is set and tracked in the variant.
 * An example would be a T-shirt where a variant is the size L.
 *
 * @see <a href="http://developers.lightspeedhq.com/ecom/endpoints/variant/">http://developers.lightspeedhq.com/ecom/endpoints/variant/</a>
 * @author stevensnoeijen
 */
@JsonIgnoreProperties({"options", "movements", "metafields", "additionalcost", "image", "stockBuyMininum", "taxType"})
@JsonRootName("variant")
public class Variant implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonRootName("variants")
    public static class List extends ArrayList<Variant> {

        public Variant[] findByProductId(int productId) {
            return stream().filter(variant -> variant.getProductId() == productId).toArray(Variant[]::new);
        }

        public Variant findDefaultByProductId(int productId) {
            return stream().filter(variant -> variant.getProductId() == productId && variant.isDefault).findFirst().orElse(null);
        }
    }

    public static enum StockTracking {
        DISABLED, ENABLED, INDICATOR;

        @JsonValue
        public String toJson() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static StockTracking fromJson(String value) {
            return Enum.valueOf(StockTracking.class, value.toUpperCase());
        }
    }

    public static enum WeightUnit {
        G, KG, OZ, LB;

        @JsonValue
        public String toJson() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static WeightUnit fromJson(String value) {
            return Enum.valueOf(WeightUnit.class, value.toUpperCase());
        }
    }

    public static enum VolumeUnit {
        ML, FL, OZ;

        @JsonValue
        public String toJson() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static VolumeUnit fromJson(String value) {
            return Enum.valueOf(VolumeUnit.class, value.toUpperCase());
        }
    }

    public static enum SizeUnit {
        CM, IN;

        @JsonValue
        public String toJson() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static SizeUnit fromJson(String value) {
            return Enum.valueOf(SizeUnit.class, value.toUpperCase());
        }
    }

    public static enum UnitUnit {
        NONE, CENTIMETER, DECIMETER, FOOT, HECTOMETER, INCH, KILOMETER, METER, MICROMETER, MILE, MILLIMETER, YARD, ACRE, ARE, HECTARES, SQUARE_CENTIMETER,
        SQUARE_FEET, SQUARE_INCH, SQUARE_KILOMETER, SQUARE_METER, SQUARE_MILE, SQUARE_MILLIMETER, SQUARE_YARD, CARAT, GRAM, KILOGRAM,
        MILLIGRAM, OUNCE, POUND, STONE, TON, TONES, CUBIC_CENTILITER, CUBIC_CENTIMETER, CUBIC_DECIMETER, CUBIC_DECAMETER,
        CUBIC_FEET, CUBIC_INCH, CUBIC_METER, CUBIC_MILLIMETER, CUBIC_YARD, DECILITER, GALLON, LITER, MILLILITER, PIECE, PAIR, CAPSULE, TABLET;

        @JsonValue
        public String toJson() {
            if (this == NONE) {
                return "";
            }

            return name().toLowerCase();
        }

        @JsonCreator
        public static UnitUnit fromJson(String value) {
            if (value == "") {
                return UnitUnit.NONE;
            }

            value = StringUtils.capitalize(value).replaceAll(" ", "_");
            return Enum.valueOf(UnitUnit.class, StringUtils.capitalize(value));
        }
    }

    /**
     * The unique numeric identifier for the variant.<br>
     * Format: <code>{“id”: “{id}”}</code>
     */
    @Getter
    private int id;

    /**
     * The date and time when the variant was created.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"createdAt": "2013-09-24T21:48:17+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime createdAt;

    /**
     * The date and time when the variant was last updated.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"updatedAt": "2013-09-24T22:02:24+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime updatedAt;

    /**
     * If the variant is the default.
     */
    @Getter
    @JsonProperty("isDefault")
    private boolean isDefault;

    /**
     * The order of the variant.<br>
     * Example: <code>{“sortOrder”:1}</code>
     */
    @Getter
    private int sortOrder;

    /**
     * Merchant specific article code.<br>
     * Example: <code>{“articleCode”: “AB00000123”}</code>
     */
    @Getter
    private String articleCode;

    /**
     * EAN (bar) code.<br>
     * Example: <code>{“ean”: “AB00000123”}</code>
     */
    @Getter
    private String ean;

    /**
     * Stock keeping unit.<br>
     * Example: <code>{“sku”: “AB00000123”}</code>
     */
    @Getter
    private String sku;

    /**
     * Price excluding VAT.<br>
     * Example: <code>{“priceExcl”:1}</code>
     */
    @Getter
    private float priceExcl;

    /**
     * Price including VAT.<br>
     * Example: <code>{“priceIncl”:1.21}</code>
     */
    @Getter
    private float priceIncl;

    /**
     * Costprice of variant (for internal use only).<br>
     * Example: <code>{“priceCost”:0.25}</code>
     */
    @Getter
    private float priceCost;

    /**
     * Old price excluding VAT (to show discounted price in the shop).<br>
     * Example: <code>{“oldPriceExcl”:1}</code>
     */
    @Getter
    private float oldPriceExcl;

    /**
     * Old price including VAT (to show discounted price in the shop).<br>
     * Example: <code>{“oldPriceIncl”:1.21}</code>
     */
    @Getter
    private float oldPriceIncl;

    /**
     * Track stock (disabled = no tracking, enabled = tracking disallow sale, indicator = stock indication, allow sale).<br>
     * Example: <code>{“stockTracking”: “disabled|enabled|indicator”}</code>
     */
    @Getter
    private StockTracking stockTracking;

    /**
     * Actual stock level.<br>
     * Example: <code>{“stockLevel”:100}</code>
     */
    @Getter
    private int stockLevel;

    /**
     * Alert stock level below threshold.<br>
     * Example: <code>{“stockAlert”:0}</code>
     */
    @Getter
    private int stockAlert;

    /**
     * Preferred minimum stockLevel.<br>
     * Example: <code>{“stockMinimum”:0}</code>
     */
    @Getter
    private int stockMinimum;

    /**
     * Amount of stock sold.<br>
     * Example: <code>{“stockSold”:0}</code>
     */
    @Getter
    private int stockSold;

    /**
     * Minimum order amount.<br>
     * Example: <code>{“stockBuyMinimum”:1}</code>
     */
    @Getter
    private int stockBuyMinimum;

    /**
     * Maximum order amount.<br>
     * Example: <code>{“stockBuyMaximum”:10000}</code>
     */
    @Getter
    private int stockBuyMaximum;

    /**
     * Weight of variant (grams).<br>
     * Example: <code>{“weight”:250}</code>
     */
    @Getter
    private int weight;

    /**
     * Weight when not in grams or when decimals are needed.<br>
     * Example: <code>{“weightValue”:250.25}</code>
     */
    @Getter
    private float weightValue;

    /**
     * Weight unit when not in grams or conversion into grams is needed.<br>
     * Example: <code>{“weightUnit”: “kg|oz|lb”}</code>
     */
    @Getter
    private WeightUnit weightUnit;

    /**
     * Volume of variant (ml).<br>
     * Example: <code>{“volume”:25}</code>
     */
    @Getter
    private int volume;

    /**
     * Volume when not in ml.<br>
     * Example: <code>{“volumeValue”:153.55}</code>
     */
    @Getter
    private float volumeValue;

    /**
     * Volume unit when not in ml or conversion into ml is needed.<br>
     * Example: <code>{“volumeUnit”: “ml|fl|oz”}</code>
     */
    @Getter
    private VolumeUnit volumeUnit;

    /**
     * Amount of colli.<br>
     * Example: <code>{“colli”:1}</code>
     */
    @Getter
    private int colli;

    /**
     * Width (cm).<br>
     * Example: <code>{“sizeX”:10}</code>
     */
    @Getter
    private int sizeX;

    /**
     * Height (cm).<br>
     * Example: <code>{“sizeY”:5}</code>
     */
    @Getter
    private int sizeY;

    /**
     * Length (cm).<br>
     * Example: <code>{“sizeZ”:3}</code>
     */
    @Getter
    private int sizeZ;

    /**
     * Width when not in cm or decimals are needed.<br>
     * Example: <code>{“sizeXValue”:33.78}</code>
     */
    @Getter
    private float sizeXValue;

    /**
     * Height when not in cm or decimals are needed.<br>
     * Example: <code>{“sizeYValue”:76.32}</code>
     */
    @Getter
    private float sizeYValue;

    /**
     * Length when not in cm or decimals are needed.<br>
     * Example: <code>{“sizeZValue”:5.7}</code>
     */
    @Getter
    private float sizeZValue;

    /**
     * Size unit when not in cm or conversion into cm is needed.<br>
     * Example: <code>{“sizeUnit”: “in”}</code>
     */
    @Getter
    private SizeUnit sizeUnit;

    /**
     * The matrix the variant relates to.<br>
     * Example: <code>{“matrix”: “A”}</code>
     */
    @Getter
    @JsonDeserialize(using = FalseNullDeserializer.class)
    private String matrix;

    /**
     * Variant title.<br>
     * Example: <code>{“title”: “Size M”}</code>
     */
    @Getter
    private String title;

    /**
     * To set a unit price.<br>
     * Example: <code>{“unitPrice”:9.8}</code>
     */
    @Getter
    private float unitPrice;

    /**
     * Unit for the price per unit.
     * Example: <code>{“unitUnit”: “Meter”}</code>
     */
    @Getter
    private UnitUnit unitUnit;

    /**
     * Link to <a href="http://developers.lightspeedhq.com/ecom/endpoints/tax/">Tax</a> resource.<br/>
     */
    @Getter
    @JsonProperty("tax")
    @JsonDeserialize(using = ResourceIdDeserializer.class)
    private int taxId;

    /**
     * Link to <a href="/ecom/endpoints/product">Product</a> resource.
     */
    @Getter
    @JsonProperty("product")
    @JsonDeserialize(using = ResourceIdDeserializer.class)
    private int productId;

}
