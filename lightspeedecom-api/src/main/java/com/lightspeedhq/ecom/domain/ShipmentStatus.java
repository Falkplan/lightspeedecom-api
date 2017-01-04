package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author stevensnoeijen
 */
public enum ShipmentStatus {
    NOT_SHIPPED, PARTIALLY_SHIPPED, SHIPPED, CANCELLED;

    @JsonValue
    public String toJson() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static ShipmentStatus fromJson(String value) {
        return Enum.valueOf(ShipmentStatus.class, value.toUpperCase());
    }
}
