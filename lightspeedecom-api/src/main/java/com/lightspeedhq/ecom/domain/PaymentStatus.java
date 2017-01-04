package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Payment status of order.
 *
 * @author stevensnoeijen
 * @see <a href="http://developers.seoshop.com/api/resources/order">http://developers.seoshop.com/api/resources/order<a/>
 */
public enum PaymentStatus {
    NOT_PAID, PARTIALLY_PAID, PAID, CANCELLED;

    @JsonValue
    public String toJson() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static PaymentStatus fromJson(String value) {
        return Enum.valueOf(PaymentStatus.class, value.toUpperCase());
    }
}
