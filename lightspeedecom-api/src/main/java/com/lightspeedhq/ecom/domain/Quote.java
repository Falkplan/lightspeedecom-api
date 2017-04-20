package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightspeedhq.ecom.LightspeedEComClient;
import com.lightspeedhq.ecom.jackson.FalseNullDeserializer;
import com.lightspeedhq.ecom.jackson.ResourceIdDeserializer;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import lombok.Getter;

/**
 * Retrieve the quotes from this shop.<br>
 * Quotes are client-side shopping cards.
 *
 * @see <A href="http://developers.lightspeedhq.com/ecom/endpoints/quote/">http://developers.lightspeedhq.com/ecom/endpoints/quote/</a>
 * @author stevensnoeijen
 */
@JsonRootName(value = "quote")
public class Quote implements Serializable {

    private static final long serialVersionUID = 2L;

    @JsonRootName("quotes")
    public static class List extends ArrayList<Quote> {
    }

    /**
     * The unique numeric identifier for the quote.<br/>
     * Example: <code>{"id": "{id}"}</code>
     */
    @Getter
    private int id;

    /**
     * The date and time when the quote was created.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"createdAt": "2013-09-24T21:48:17+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime createdAt;

    /**
     * The date and time when the quote was last updated.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"updatedAt": "2013-09-24T22:02:24+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime updatedAt;

    /**
     * The prices within a quote are recalculated when the quote has changed.<br>
     * Example: <code>{“recalculatedAt”: “2013-09-20T17:33:18+02:00”}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime recalculatedAt;

    /**
     * Is the quote locked?
     * Format: <code>{“isLocked”: “true|false”}</code>
     */
    @Getter
    @JsonProperty("isLocked")
    private boolean isLocked;

    /**
     * The channel that created the quote.<br>
     * Format: <code>{“channel”: “main|facebook|mobile|api”}</code>
     */
    @Getter
    private Channel channel;

    /**
     * RecoveryHash can be used to recover the cart in the shopping card.
     */
    @Getter
    private String recoveryHash;

    /**
     * The ip address from the initializer of the quote, usually the customer.<br>
     * Example: <code>{“remoteIp”: “::1”}</code>
     */
    @Getter
    private String remoteIp;

    /**
     * The user agent of the initializer, usually the customer.<br>
     * Example: <code>{“userAgent”: “”}</code>
     */
    @Getter
    private String userAgent;

    /**
     * A referral id value.<br>
     * Example: <code>{“referralId”: “”}</code>
     */
    @Getter
    @JsonDeserialize(using = FalseNullDeserializer.class)
    private String referralId;

    /**
     * The total weight of the quote.<br>
     * Example: <code>{“weight”:250}</code>
     */
    @Getter
    private int weight;

    /**
     * The total volume of the quote.<br>
     * Example: <code>{“volume”:25}</code>
     */
    @Getter
    private int volume;

    /**
     * The total colli of the quote.<br>
     * Example: <code>{“colli”:1}</code>
     */
    @Getter
    private int colli;

    /**
     * The customer country for retrieving the available payment methods.<br>
     * Example: <code>{“paymentCountry”: *{“id”: 150, “code”: “nl”, “code3”: “nld”, “title”: “Netherlands, The”}</code>
     */
    @Getter
    private Country paymentCountry;

    /**
     * The customer country for retrieving the available shipment methods.<br>
     * Example: <code>{“shipmentCountry”: *{“id”: 150, “code”: “nl”, “code3”: “nld”, “title”: “Netherlands, The” }</code>
     */
    @Getter
    private Country shipmentCountry;

    /**
     * The customer zipcode for retrieving the available shipment methods.<br>
     * Example: <code>{“shipmentZipcode”: “1016 EE”}</code>
     */
    @Getter
    private String shipmentZipcode;

    /**
     * Returns true if the billing and shipping address are the same.<br>
     * Example: <code>{“shipmentSameAddress”: “true|false”}</code>
     */
    @Getter
    private boolean shipmentSameAddress;

    /**
     * The total cost price of the quote.<br>
     * Example: <code>{“priceCost”:0.25}</code>
     */
    @Getter
    private float priceCost;

    /**
     * The total price excluding VAT.<br>
     * Example: <code>{“priceExcl”:49.08}</code>
     */
    @Getter
    private float priceExcl;

    /**
     * The total price including VAT.<br>
     * Example: <code>{“priceIncl”:49.95}</code>
     */
    @Getter
    private float priceIncl;

    /**
     * The total discount excluding VAT.<br>
     * Example: <code>{“discountExcl”:2.5}</code>
     */
    @Getter
    private float discountExcl;

    /**
     * The total discount including VAT.<br>
     * Example: <code>{“discountIncl”:0}</code>
     */
    @Getter
    private float discountIncl;

    /**
     * The total count of products.<br>
     * Example: <code>{“productsCount”:1}</code>
     */
    @Getter
    private int productsCount;

    /**
     * The total quantity of product.<br>
     * Example: <code>{“productsQuantity”:1}</code>
     */
    @Getter
    private int productsQuantity;

    /**
     * Returns true if a shipment method has been chosen.<br>
     * Example: <code>{“shipmentIsSet”: “true|false”}</code>
     */
    @Getter
    private boolean shipmentIsSet;

    /**
     * The chosen shipment method.<br>
     * Example: <code>{“shipmentId”: “core|15552|46029”}</code>
     */
    @Getter
    private String shipmentId;

    /**
     * Returns true if the shipment method is virtual.<br>
     * Example: <code>{“shipmentIsVirtual”: “true|false”}</code>
     */
    @Getter
    private boolean shipmentIsVirtual;

    /**
     * Returns true if the shipment method is cash on delivery.<br>
     * Example: <code>{“shipmentIsCashOnDelivery”: “true|false”}</code>
     */
    @Getter
    private boolean shipmentIsCashOnDelivery;

    /**
     * The discount in percentage.<br>
     * Example: <code>{“shipmentDiscountPercentage”:0.25}</code>
     */
    @Getter
    private float shipmentDiscountPercentage;

    /**
     * Returns true if the shipment method is a service point.<br>
     * Example: <code>{“shipmentIsServicePoint”: “true|false”}</code>
     */
    @Getter
    private boolean shipmentIsServicePoint;

    /**
     * Returns true if the shipment method is a pick up.<br>
     * Example: <code>{“shipmentIsPickup”: “true|false”}</code>
     */
    @Getter
    private boolean shipmentIsPickup;

    /**
     * The tax rate in percentage.<br>
     * Example: <code>{“shipmentTaxRate”:0.21}</code>
     */
    @Getter
    private float shipmentTaxRate;

    /**
     * The shipment base price excluding VAT.<br>
     * Example: <code>{“shipmentBasePriceExcl”:4.1322}</code>
     */
    @Getter
    private float shipmentBasePriceExcl;

    /**
     * The shipment base price including VAT.<br>
     * Example: <code>{“shipmentBasePriceIncl”:5}</code>
     */
    @Getter
    private float shipmentBasePriceIncl;

    /**
     * The shipment price tax in percentage.<br>
     * Example: <code>{“shipmentPriceTax”:0.21}</code>
     */
    @Getter
    private float shipmentPriceTax;

    /**
     * The shipment price excluding VAT.<br>
     * Example: <code>{“shipmentPriceExcl”:4.13}</code>
     */
    @Getter
    private float shipmentPriceExcl;

    /**
     * The shipment price including VAT.<br>
     * Example: <code>{“shipmentPriceIncl”:5}</code>
     */
    @Getter
    private float shipmentPriceIncl;

    /**
     * The shipment discount price excluding VAT.<br>
     * Example: <code>{“shipmentDiscountExcl”:0}</code>
     */
    @Getter
    private float shipmentDiscountExcl;

    /**
     * The shipment discount price including VAT.<br>
     * Example: <code>{“shipmentDiscountIncl”:0}</code>
     */
    @Getter
    private float shipmentDiscountIncl;

    /**
     * The title of the shipment method.<br>
     * Example: <code>{“shipmentTitle”: “Default shipment method”}</code>
     */
    @Getter
    private String shipmentTitle;

    /**
     * Returns true when a payment method has been chosen.<br>
     * Example: <code>{“paymentIsSet”: “true|false”}</code>
     */
    @Getter
    private boolean paymentIsSet;

    /**
     * Property description.<br>
     * Example: <code>{“paymentId”: “invoice”}</code>
     */
    @Getter
    private String paymentId;

    /**
     * Returns true when the payment will be completed after delivery of the order.<br>
     * Example: <code>{“paymentIsPost”: “true|false”}</code>
     */
    @Getter
    private boolean paymentIsPost;

    /**
     * Returns true when the invoicing is handled by an external service.<br>
     * Example: <code>{“paymentIsInvoiceExternal”: “true|false”}</code>
     */
    @Getter
    private boolean paymentIsInvoiceExternal;

    /**
     * Additional payment data like the chosen method eg. iDeal, Mastercard, or others.<br>
     * Data is in json format.<br>
     * Example: <code>{“paymentData”: []}</code>
     */
    @Getter
    private String paymentData;

    /**
     * The payment tax rate in percentage.<br>
     * Example: <code>{“paymentTaxRate”:0.21}</code>
     */
    @Getter
    private float paymentTaxRate;

    /**
     * The payment base price excluding VAT.<br>
     * Example: <code>{“paymentBasePriceExcl”:1}</code>
     */
    @Getter
    private float paymentBasePriceExcl;

    /**
     * The payment base price including VAT.<br>
     * Example: <code>{“paymentBasePriceIncl”:1.21}</code>
     */
    @Getter
    private float paymentBasePriceIncl;

    /**
     * The payment price tax in percentage.<br>
     * Example: <code>{“paymentPriceTax”:0.21}</code>
     */
    @Getter
    private float paymentPriceTax;

    /**
     * The payment price excluding VAT.<br>
     * Example: <code>{“paymentPriceExcl”:1}</code>
     */
    @Getter
    private float paymentPriceExcl;

    /**
     * The payment price including VAT.<br>
     * Example: <code>{“paymentPriceIncl”:1.21}</code>
     */
    @Getter
    private float paymentPriceIncl;

    /**
     * Additional cost including VAT.<br>
     * Example: <code>{"additionalCostIncl": "1.0000"}</code>
     */
    @Getter
    private String additionalCostIncl;

    /**
     * Additional cost excluding VAT.<br>
     * Example: <code>{"additionalCostExcl": "0.8264"}</code>
     */
    @Getter
    private String additionalCostExcl;

    /**
     * The title of the chosen payment method.<br>
     * Example: <code>{“paymentTitle”: “SEOshop Payments - Mastercard”}</code>
     */
    @Getter
    private String paymentTitle;

    /**
     * Returns true if a discount code has been applied.<br>
     * Example: <code>{“discountIsSet”: “true|false”}</code>
     */
    @Getter
    private boolean discountIsSet;

    /**
     * The unique identifier that links to the discount code.<br>
     * Example: <code>{“discountId”:0}</code>
     */
    @Getter
    private int discountId;

    /**
     * The type of discount that has been applied.<br>
     * Example: <code>{“discountType”: “amount”}</code>
     */
    @Getter
    private String discountType;

    /**
     * The total amount of the discount.<br>
     * Example: <code>{“discountAmount”:2.5}</code>
     */
    @Getter
    private float discountAmount;

    /**
     * The total percentage of the discount.<br>
     * Example: <code>{“discountPercentage”:0.25}</code>
     */
    @Getter
    private float discountPercentage;

    /**
     * The type of discount applied to the shipment method.<br>
     * Example: <code>{“discountShipment”: “default, discount, free”}</code>
     */
    @Getter
    private String discountShipment;

    /**
     * The minimum order amount to allow the discount to be applied.<br>
     * Example: <code>{“discountMinimumAmount”:45}</code>
     */
    @Getter
    private String discountMinimumAmount;

    /**
     * The discount code that has been applied to this quote.<br>
     * Example: <code>{“discountCouponCode”: “summer2015”}</code>
     */
    @Getter
    private String discountCouponCode;

    /**
     * A customer comment.<br>
     * Example: <code>{“comment”: “Could you wrap it? Its a present.”}</code>
     */
    @Getter
    private String comment;

    /**
     * Allow the user to be notified for changes in the order?
     */
    @Getter
    private boolean allowNotifications;

    /**
     * The customer id, if the customer has logged in.<br>
     * Example: <code>{“customer”:2}</code>
     */
    @Getter
    @JsonProperty("customer")
    @JsonDeserialize(using = ResourceIdDeserializer.class)
    private int customerId;

    /**
     * The language the quote has been created in.<br>
     * Example: <code>{“language”: {“id”: 1,“code”: “nl”,“locale”: “nl_NL”,“title”: “Nederlands”}}</code>
     */
    @Getter
    private Language language;

}
