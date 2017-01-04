package com.lightspeedhq.ecom;

import com.lightspeedhq.ecom.domain.LightspeedEComError;
import feign.FeignException;
import lombok.Getter;

/**
 *
 * @author stevensnoeijen
 */
public class LightspeedEComErrorException extends FeignException {

    @Getter
    private LightspeedEComError error;

    public LightspeedEComErrorException(String message, LightspeedEComError error) {
        super(message);
        this.error = error;
    }

    @Override
    public String toString() {
        return error.toString();
    }
}
