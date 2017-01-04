package com.lightspeedhq.ecom;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Decodes {@link LightspeedEComClient#HEADER_RATELIMIT_LIMIT} and updates the {@link Limiter}
 *
 * @author stevensnoeijen
 */
public class LimitDecoder implements Decoder {

    private Decoder responseDecoder;
    private Limiter limiter;

    public LimitDecoder(Decoder responseDecoder, Limiter limiter) {
        this.responseDecoder = responseDecoder;
        this.limiter = limiter;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        limiter.update(response);
        Object object = responseDecoder.decode(response, type);
        return object;
    }

}
