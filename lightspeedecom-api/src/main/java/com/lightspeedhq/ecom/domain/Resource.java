package com.lightspeedhq.ecom.domain;

import lombok.Getter;

/**
 * Link to another resource
 *
 * @author stevensnoeijen
 */
public class Resource {

    @Getter
    private int id;

    @Getter
    private String url;

    @Getter
    private String link;
}
