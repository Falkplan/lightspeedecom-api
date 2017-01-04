package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author stevensnoeijen
 */
public enum Gender {
    MALE, FEMALE;

    @JsonValue
    public String toJson() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static Gender fromJson(String value) {
        return Enum.valueOf(Gender.class, value.toUpperCase());
    }
}
