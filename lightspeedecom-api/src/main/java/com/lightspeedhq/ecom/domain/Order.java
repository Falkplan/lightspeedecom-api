package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lightspeedhq.ecom.LightspeedEComClient;
import com.lightspeedhq.ecom.jackson.FalseNullDeserializer;
import com.lightspeedhq.ecom.jackson.FalseNullSerializer;
import com.lightspeedhq.ecom.jackson.ResourceIdDeserializer;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * Representation of an order.
 * Orders have one customer, can have many shipments, can have many invoices and can have many products.
 * Orders can be created from the backoffice (offer) or from the frontend (quote).
 * Orders cannot be created directly from the API.
 *
 * @author stevensnoeijen
 * @see <a href="http://developers.seoshop.com/api/resources/order">http://developers.seoshop.com/api/resources/order</a>
 */
@JsonIgnoreProperties({"paymentTaxRates", "shipmentTaxRates", "taxRates", "invoices",
    "shipments", "products", "metafields", "quote", "events"})
@JsonRootName("order")
public class Order implements Serializable {

    private static final long serialVersionUID = 2L;

    @JsonRootName("orders")
    public static class List extends ArrayList<Order> {

    }

    /**
     * Order status
     */
    public static enum OrderStatus {
        NEW,
        OFFER,
        ON_HOLD,
        PROCESSING,
        PROCESSING_AWAITING_PAYMENT,
        PROCESSING_AWAITING_SHIPMENT,
        PROCESSING_AWAITING_PICKUP,
        PROCESSING_PARTIALLY_PAID,
        PROCESSING_PARTIALLY_SHIPPED,
        PROCESSING_PARTIALLY_PICKED_UP,
        COMPLETED,
        COMPLETED_SHIPPED,
        COMPLETED_PICKED_UP,
        CANCELLED;

        @JsonValue
        public String toJson() {
            return this.name().toLowerCase();
        }

        @JsonCreator
        public static OrderStatus fromJson(String value) {
            return Enum.valueOf(OrderStatus.class, value.toUpperCase());
        }
    }

    /**
     * The unique numeric identifier for the order.<br>
     * Example: <code>{"id": "{id}"}</code>
     */
    @Getter
    private int id;

    /**
     * The date and time when the order was created.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"createdAt": "2013-09-24T21:48:17+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime createdAt;

    /**
     * The date and time when the order was last updated.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"updatedAt": "2013-09-24T22:02:24+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime updatedAt;

    /**
     * Order number.<br>
     * Example: <code>{"number": "ORD00001"}</code>
     */
    @Getter
    private String number;

    /**
     * Order status.
     */
    @Getter
    private OrderStatus status;

    /**
     * Custom status id.<br>
     * Format: <code>{"customStatusId": "{custom_status_id}|null"}</code>
     */
    @Getter
    private Integer customStatusId;

    /**
     * The channel trough which the order was entered.<br>
     * Format: <code>{"channel": "main|facebook|api|mobile"}</code>
     */
    @Getter
    private Channel channel;

    /**
     * The IP-address of the customer.<br>
     * Format: <code>{"remoteIp": "xxx.xxx.xxx.xxx"}</code>
     */
    @Getter
    private String remoteIp;

    /**
     * The user-agent of the customer.M<br>
     * Example: <code>{"userAgent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3)"}</code>
     */
    @Getter
    private String userAgent;

    /**
     * Value of referral_id from query parameters. Can be used for tracking.<br>
     * Format: <code>{"referralId": "{referral_id}|false"}</code>
     */
    @Getter
    @JsonDeserialize(using = FalseNullDeserializer.class)
    private Integer referralId;

    /**
     * Cost price of order products.<br>
     * (precision: 4)<br>
     * Example: <code>{"priceCost":0}</code>
     */
    @Getter
    private float priceCost;

    /**
     * Order price excl. VAT.<br>
     * (precision: 4)<br>
     * Example: <code>{"priceExcl":0}</code>
     */
    @Getter
    private float priceExcl;

    /**
     * Order price incl. VAT.<br>
     * (precision: 4)<br>
     * Example: <code>{"priceIncl":0}</code>
     */
    @Getter
    private float priceIncl;

    /**
     * Total weight of order products.<br>
     * Example: <code>{"weight":250}</code>
     */
    @Getter
    private int weight;

    /**
     * Total volume of order products.<br>
     * Example: <code>{"volume":25}</code>
     */
    @Getter
    private int volume;

    /**
     * Total amount of colli for order products.<br>
     * Example: <code>{"colli":1}</code>
     */
    @Getter
    private int colli;

    /**
     * The gender of the customer.<br/>
     * Example: <code>{"gender": "male|female"}</code>
     */
    @Getter
    @Setter
    @JsonDeserialize(using = FalseNullDeserializer.class)
    @JsonSerialize(using = FalseNullSerializer.class)
    private Gender gender;

    /**
     * The birthdate of the customer.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"birthDate": "1991-01-01"}</code>
     */
    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATE_FORMAT)
    @JsonDeserialize(using = FalseNullDeserializer.class)
    private Date birthDate;

    /**
     * The National Identification Number (BSN) of the customer.<br/>
     * Example: <code>{"nationalId": ""}</code>
     */
    @Getter
    @Setter
    private String nationalId;

    /**
     * The email address of the customer.<br/>
     * Example: <code>{"email": "info@seoshop.com"}</code>
     */
    @Getter
    @Setter
    private String email;

    /**
     * The firstname of the customer.<br/>
     * Example: <code>{"firstname": "Jan"}</code>
     */
    @Getter
    @Setter
    private String firstname;

    /**
     * The middlename of the customer.<br/>
     * Example: <code>{"middlename": ""}</code>
     */
    @Getter
    @Setter
    private String middlename;

    /**
     * The lastname of the customer.<br/>
     * Example: <code>{"lastname": "Janssen"}</code>
     */
    @Getter
    @Setter
    private String lastname;

    /**
     * The phonenumber of the customer.<br/>
     * Example: <code>{"phone": ""}</code>
     */
    @Getter
    @Setter
    private String phone;

    /**
     * The mobile phone of the customer.<br/>
     * Example: <code>{"mobile": ""}</code>
     */
    @Getter
    @Setter
    private String mobile;

    /**
     * If the customer is a company.
     * Example: <code>{"isCompany": "true|false"}</code>
     */
    @JsonProperty("isCompany")
    @Getter
    @Setter
    private boolean isCompany;

    /**
     * The company name of the customer.<br/>
     * Example: <code>{"companyName": "SEOshop"}</code>
     */
    @Getter
    @Setter
    private String companyName;

    /**
     * The company CoC number.<br/>
     * Example: <code>{"companyCoCNumber":12345678}</code>
     */
    @Getter
    @Setter
    private String companyCoCNumber;

    /**
     * The company VAT number.<br/>
     * Example: <code>{"companyVatNumber": "AB12308839"}</code>
     */
    @Getter
    @Setter
    private String companyVatNumber;

    /**
     * Billing address name.<br/>
     * Example: <code>{"addressBillingName": "Ruud Stelder"}</code>
     */
    @Getter
    @Setter
    private String addressBillingName;

    /**
     * Billing address street.<br/>
     * Example: </code>{"addressBillingStreet": "Keizersgracht"}</code>
     */
    @Getter
    @Setter
    private String addressBillingStreet;

    /**
     * Billing address line 2.<br/>
     * Example: <code>{"addressBillingStreet2": ""}</code>
     */
    @Getter
    @Setter
    private String addressBillingStreet2;

    /**
     * Billing address housenumber.<br/>
     * Example: <code>{"addressBillingNumber":313}</code>
     */
    @Getter
    @Setter
    private String addressBillingNumber;

    /**
     * Billing address housenumber extension.<br/>
     * Example: <code>{"addressBillingExtension": ""}</code>
     */
    @Getter
    @Setter
    private String addressBillingExtension;

    /**
     * Billing address zipcode.<br/>
     * Example: <code>{"addressBillingZipcode": "1016 EE"}</code>
     */
    @Getter
    @Setter
    private String addressBillingZipcode;

    /**
     * Billing address city.<br/>
     * Example: <code>{"addressBillingCity": "Amsterdam"}</code>
     */
    @Getter
    @Setter
    private String addressBillingCity;

    /**
     * Billing address region.<br/>
     * Example: <code>{"addressBillingRegion": "Noord-Holland"}</code>
     */
    @Getter
    @Setter
    private String addressBillingRegion;

    /**
     * Billing address country object.<br/>
     * Do not use for changing the country, use {@link Customer#setAddressBillingCountry(String)}.<br>
     * Example: <code>{"addressBillingCountry": { "id": 150, "code": "nl", "code3": "nld", "title": "Netherlands, The" } }</code>
     */
    @Getter
    private Country addressBillingCountry;

    /**
     * Shipping address companyname.<br/>
     * Example: <code>{"addressShippingCompany": "SEOshop"}</code>
     */
    @Getter
    @Setter
    @JsonDeserialize(using = FalseNullDeserializer.class)
    @JsonSerialize(nullsUsing = FalseNullSerializer.class)
    private String addressShippingCompany;

    /**
     * Shipping address name.<br/>
     * Example: <code>{"addressShippingName": "Ruud Stelder"}</code>
     */
    @Getter
    @Setter
    private String addressShippingName;

    /**
     * Shipping address street.<br/>
     * Example: <code>{"addressShippingStreet": "Keizersgracht"}</code>
     */
    @Getter
    @Setter
    private String addressShippingStreet;

    /**
     * Shipping address line 2.<br/>
     * Example: <code>{"addressShippingStreet2": ""}</code>
     */
    @Getter
    @Setter
    private String addressShippingStreet2;

    /**
     * Shipping address housenumber.<br/>
     * Example: <code>{"addressShippingNumber":313}</code>
     */
    @Getter
    @Setter
    private String addressShippingNumber;

    /**
     * Shipping address housenumber extension.<br/>
     * Example: <code>{"addressShippingExtension": ""}</code>
     */
    @Getter
    @Setter
    private String addressShippingExtension;

    /**
     * Shipping address zipcode.<br/>
     * Example: <code>{"addressShippingZipcode": "1016 EE"}</code>
     */
    @Getter
    @Setter
    private String addressShippingZipcode;

    /**
     * Shipping address city.<br/>
     * Example: <code>{"addressShippingCity": "Amsterdam"}</code>
     */
    @Getter
    @Setter
    private String addressShippingCity;

    /**
     * Shipping address region.<br/>
     * Example: <code>{"addressShippingRegion": "Noord-Holland"}</code>
     */
    @Getter
    @Setter
    private String addressShippingRegion;

    /**
     * Shipping address country object.<br/>
     * Do not use for changing the country, use {@link Customer#setAddressShippingCountry(String)}.<br>
     * Example: <code>{ "addressShippingCountry": { "id": 150, "code": "nl", "code3": "nld", "title": "Netherlands, The" } }</code>
     */
    @Getter
    private Country addressShippingCountry;

    /**
     * Unique (internal) identifier of payment method.<br/>
     * Example: <code>{"paymentId": "seoshoppayments"}</code>
     */
    @Getter
    private String paymentId;

    /**
     * Payment status of order.<br>
     * Format: <code>{"paymentStatus": "not_paid, partially_paid, paid, cancelled"}</code>
     */
    @Getter
    private PaymentStatus paymentStatus;

    /**
     * Order will be paid after it has been delivered.<br>
     * Example: <code>{"paymentIsPost":true}</code>
     */
    @Getter
    private boolean paymentIsPost;

    /**
     * Payment invoice is handled by third-party.<br>
     * Example: <code>{"paymentIsInvoiceExternal":true}</code>
     */
    @Getter
    private boolean paymentIsInvoiceExternal;

    /**
     * Payment tax rate (percentage / 100).<br>
     * Example: <code>{"paymentTaxRate":0}</code>
     */
    @Getter
    private float paymentTaxRate;

    /**
     * Payment Base Price Excl. VAT.<br>
     * Example: <code>{"paymentBasePriceExcl":0}</code>
     */
    @Getter
    private float paymentBasePriceExcl;

    /**
     * Payment Base Price Incl. VAT.<br>
     * Example: <code>{"paymentBasePriceIncl":0}</code>
     */
    @Getter
    private float paymentBasePriceIncl;

    /**
     * Payment Price Excl. VAT.<br>
     * Example: <code>{"paymentPriceExcl":0}</code>
     */
    @Getter
    private float paymentPriceExcl;

    /**
     * Payment Price Incl. VAT.<br>
     * Example: <code>{"paymentPriceIncl":0}</code>
     */
    @Getter
    private float paymentPriceIncl;

    /**
     * Title of payment as displayed to customer (PSP - Method).<br>
     * Example: <code>{"paymentTitle": "SEOshop Payments - iDeal"}</code>
     */
    @Getter
    private String paymentTitle;

    /**
     * This field contains additionally stored data from the payment method.<br>
     * Example: <code>
     * {
     *  "paymentData": {
     *      "uuid": "12345",
     *      "device": "Apple Watch"
     *  }
     * }
     * </code>
     */
    @Getter
    private Map<String, String> paymentData;

    /**
     * Unique (internal) identifier of shipping method.<br>
     * Example: <code>{"shipmentId": "core|1|1"}</code>
     */
    @Getter
    private String shipmentId;

    /**
     * Shipment status.<br>
     * Example: <code>{"shipmentStatus": "not_shipped, partially_shipped, shipped, cancelled"}</code>
     */
    @Getter
    private ShipmentStatus shipmentStatus;

    /**
     * Shipment is cash on delivery.<br>
     * Example: <code>{"shipmentIsCashOnDelivery":true}</code>
     */
    @Getter
    private boolean shipmentIsCashOnDelivery;

    /**
     * Shipment is pickup in store.<br>
     * Example: <code>{"shipmentIsPickup":true}</code>
     */
    @Getter
    private boolean shipmentIsPickup;

    /**
     * Shipping taxrate (percentage / 100).<br>
     * Example: <code>{"shipmentTaxRate":0}</code>
     */
    @Getter
    private float shipmentTaxRate;

    /**
     * Shipping Base Price Excl. VAT.<br>
     * Example: <code>{"shipmentBasePriceExcl":0}</code>
     */
    @Getter
    private float shipmentBasePriceExcl;

    /**
     * Shipping Base Price Incl. VAT.<br>
     * Example: <code>{"shipmentBasePriceIncl":0}</code>
     */
    @Getter
    private float shipmentBasePriceIncl;

    /**
     * Shipping price Excl. VAT.<br>
     * Example: <code>{"shipmentPriceExcl":0}</code>
     */
    @Getter
    private float shipmentPriceExcl;

    /**
     * Shipping Price Incl. VAT.<br>
     * Example: <code>{"shipmentPriceIncl":0}</code>
     */
    @Getter
    private float shipmentPriceIncl;

    /**
     * Shipping Discount Excl. VAT.<br>
     * Example: <code>{"shipmentDiscountExcl":0}</code>
     */
    @Getter
    private float shipmentDiscountExcl;

    /**
     * Shipping Discount Incl. VAT.<br>
     * Example: <code>{"shipmentDiscountIncl":0}</code>
     */
    @Getter
    private float shipmentDiscountIncl;

    /**
     * Title of shipment as displayed to customer.<br>
     * Example: <code>{"shipmentTitle": "Default shipment method"}</code>
     */
    @Getter
    private String shipmentTitle;

    /**
     * This field contains additionally stored data from the shipment method.<br>
     * Example: <code>
     * {
     * "shipmentData": {
     * "deliver_at_neighbours": false,
     * "delivery_time": "10:00"
     * }
     * }
     * </code>
     */
    @Getter
    private Map<String, String> shipmentData;

    /**
     * If a delivery date was chosen,
     * the shipping date is the date on which you must ship the package for it to arrive on the chosen delivery date.<br>
     * Example: <code>{"shippingDate": "2015-05-20"}</code>
     */
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATE_FORMAT)
    private Date shippingDate;

    /**
     * The delivery date as chosen by the customer during the checkout.<br>
     * Example: <code>{"deliveryDate": "2015-05-21"}</code>
     */
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATE_FORMAT)
    private Date deliveryDate;

    /**
     * Is Order discounted.<br>
     * Example: <code>{"isDiscounted":true}</code>
     */
    @Getter
    @JsonProperty("isDiscounted")
    private boolean isDiscounted;

    /**
     * Discount type.<br>
     * Example: <code>{"discountType": ""}</code>
     */
    //TODO: make enum
    @Getter
    private String discountType;

    /**
     * Amount of discount given.<br>
     * Example: <code>{"discountAmount":0}</code>
     */
    @Getter
    private float discountAmount;

    /**
     * Percentage of discount given (percentage / 100).<br>
     * Example: <code>{"discountPercentage":0}</code>
     */
    @Getter
    private float discountPercentage;

    /**
     * Discount coupon used.<br>
     * Example: <code>{"discountCouponCode": ""}</code>
     */
    @Getter
    private String discountCouponCode;

    /**
     * Customer is new or returning.<br>
     * Example: <code>{"isNewCustomer":true}</code>
     */
    @Getter
    @JsonProperty("isNewCustomer")
    private boolean isNewCustomer;

    /**
     * Customer comment.<br>
     * Example: <code>{"comment": ""}</code>
     */
    @Getter
    private String comment;

    /**
     * Merchant memo.<br>
     * Example: <code>{"memo": "Customer called and wants the product wrapped."}</code>
     */
    @Getter
    private String memo;

    /**
     * undocumented
     */
    @Getter
    private boolean allowNotifications;

    /**
     * Notify customer when new.<br>
     * Example: <code>{"doNotifyNew":true}</code>
     */
    @Getter
    private boolean doNotifyNew;

    /**
     * Notify customer when payment overdue.<br>
     * Example: <code>{"doNotifyReminder":true}</code>
     */
    @Getter
    private boolean doNotifyReminder;

    /**
     * Notify customer when cancelled.<br>
     * Example: <code>{"doNotifyCancelled":true}</code>
     */
    @Getter
    private boolean doNotifyCancelled;

    /**
     * {@link Language} object.
     */
    @Getter
    @JsonDeserialize(using = FalseNullDeserializer.class)
    private Language language;

    /**
     * Link to {@link Customer} resource
     */
    @Getter
    @JsonProperty("customer")
    @JsonDeserialize(using = ResourceIdDeserializer.class)
    private int customerId;
}
