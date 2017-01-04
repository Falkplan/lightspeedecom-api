package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The channel trough which the order was entered.
 *
 * @author stevensnoeijen
 */
public enum Channel {
    MAIN, FACEBOOK, API, MOBILE;

    @JsonValue
    public String toJson() {
        return this.name().toLowerCase();
    }

    @JsonCreator
    public static Channel fromJson(String value) {
        return Enum.valueOf(Channel.class, value.toUpperCase());
    }
}
