package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.lightspeedhq.ecom.LightspeedEComClient;
import com.lightspeedhq.ecom.jackson.FalseNullDeserializer;
import com.lightspeedhq.ecom.jackson.FalseNullSerializer;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * The customer endpoints shows all customers that ordered something trough the shop.<br/>
 * A customer belongs to an order, an order has one customer.
 *
 * @see <a href="http://developers.seoshop.com/api/resources/customer">http://developers.seoshop.com/api/resources/customer</a>
 * @author stevensnoeijen
 */
@JsonRootName("customer")
@JsonIgnoreProperties({"groups", "invoices", "orders", "reviews", "shipments", "tickets", "metafields", "login"})
public class Customer implements Serializable {

    private static final long serialVersionUID = 2L;

    @JsonRootName("customers")
    public static class List extends ArrayList<Customer> {

    }

    /**
     * The unique numeric identifier for the customer.<br/>
     * Example: <code>{"id": "{id}"}</code>
     */
    @Getter
    private int id;

    /**
     * The date and time when the customer was created.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"createdAt": "2013-09-24T21:48:17+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime createdAt;

    /**
     * The date and time when the customer was last updated.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"createdAt": "2013-09-24T21:48:17+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime updatedAt;

    /**
     * The last time the customer was online at the shop.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"updatedAt": "2013-09-24T22:02:24+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime lastOnlineAt;

    /**
     * If the customer is confirmed.<br/>
     * Example: <code>{"isConfirmed":true}</code>
     */
    @JsonProperty("isConfirmed")
    @Getter
    @Setter
    private boolean isConfirmed;

    @Getter
    private String type;

    /**
     * The remote IP address.<br/>
     * Example: <code>{"remoteIp": "127.0.0.1"}</code>
     */
    @Getter
    @JsonDeserialize(using = FalseNullDeserializer.class)
    private String remoteIp;

    /**
     * User Agent.<br/>
     * Example: <code>{"userAgent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36"}</code>
     */
    @Getter
    @JsonDeserialize(using = FalseNullDeserializer.class)
    private String userAgent;

    /**
     * The affiliate key.<br/>
     * Example: <code>{"referralId": ""}</code>
     */
    @Getter
    @Setter
    @JsonDeserialize(using = FalseNullDeserializer.class)
    @JsonSerialize(using = FalseNullSerializer.class)
    private String referralId;

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
    @JsonSerialize(using = FalseNullSerializer.class)
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
    @Setter
    @JsonDeserialize(using = FalseNullDeserializer.class)
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonDeserialize(using = FalseNullDeserializer.class)
    @JsonSerialize(using = ToStringSerializer.class)
    private Country addressShippingCountry;

    /**
     * Internal memo.<br/>
     * Example: <code>{"memo": "Customer called and asked if we could wrap it."}</code>
     */
    @Getter
    @Setter
    private String memo;

    /**
     * Notify customer when registered.<br/>
     * Example: <code>{"doNotifyRegistered": "true|false"}</code>
     */
    @Getter
    @Setter
    private boolean doNotifyRegistered;

    /**
     * Notify customer when confirmed.<br/>
     * Example: <code>{"doNotifyConfirmed": "true|false"}</code>
     */
    @Getter
    @Setter
    private boolean doNotifyConfirmed;

    /**
     * Notify customer about password.<br/>
     * Example: <code>{"doNotifyPassword": "true|false"}</code>
     */
    @Getter
    @Setter
    private boolean doNotifyPassword;

    /**
     * Customer language object.<br/>
     * Do not use for changing the language, use {@link Customer#setLanguage(String)}.<br>
     * Example: <code>{ "language": { "id": 1, "code": "nl", "locale": "nl_NL", "title": "Nederlands" } }</code>
     */
    @Getter
    @JsonDeserialize(using = FalseNullDeserializer.class)
    private Language language;

    /**
     * Sets the minimal arguments to create a user in the api.
     *
     * @param email
     * @param firstname
     * @param lastname
     *
     * @return customer with the parameters
     */
    public static Customer create(String email, String firstname, String lastname) {
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setFirstname(firstname);
        customer.setLastname(lastname);
        return customer;
    }

    @JsonIgnore
    public void setAddressBillingCountry(String countryCode) {
        this.addressBillingCountry = new Country(-1, countryCode, null, null);
    }

    @JsonIgnore
    public void setAddressShippingCountry(String countryCode) {
        this.addressShippingCountry = new Country(-1, countryCode, null, null);
    }
}
