package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author stevensnoeijen
 */
@ToString
@JsonRootName("error")
public class LightspeedEComError {

    @Getter
    private int code;

    @Getter
    private String method;

    @Getter
    private String request;

    @Getter
    private String message;
}
