package com.lightspeedhq.ecom;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 *
 * @author stevensnoeijen
 */
public class LimitRequestInterceptor implements RequestInterceptor {

    private Limiter limiter;

    public LimitRequestInterceptor(Limiter limiter) {
        this.limiter = limiter;
    }

    @Override
    public void apply(RequestTemplate template) {
        limiter.waitOrContinue();
    }

}
