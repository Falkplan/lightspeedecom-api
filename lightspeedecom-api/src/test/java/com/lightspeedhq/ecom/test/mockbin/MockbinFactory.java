package com.lightspeedhq.ecom.test.mockbin;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import static com.lightspeedhq.ecom.test.mockbin.MockbinClient.DOMAIN;

/**
 *
 * @author stevensnoeijen
 */
public class MockbinFactory {

    public static MockbinClient create() {
        MockbinClient mockbinClient = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(MockbinClient.class, MockbinClient.DOMAIN);
        return mockbinClient;
    }

    public static String getBinUrl(String id) {
        return DOMAIN + "/bin/" + id;
    }
}
