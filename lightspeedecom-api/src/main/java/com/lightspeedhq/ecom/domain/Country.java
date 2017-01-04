package com.lightspeedhq.ecom.domain;

import java.io.Serializable;
import lombok.Getter;

/**
 * Used in {@link Customer} and {@link Supplier}
 *
 * @author stevensnoeijen
 */
//@JsonRootName("country")
public class Country implements Serializable {

    private static final long serialVersionUID = 2L;

    @Getter
    private int id;

    @Getter
    private String code;

    @Getter
    private String code3;

    @Getter
    private String title;

    public Country() {
    }

    public Country(int id, String code, String code3, String title) {
        this.id = id;
        this.code = code;
        this.code3 = code3;
        this.title = title;
    }

    @Override
    public String toString() {
        return code;
    }
}
