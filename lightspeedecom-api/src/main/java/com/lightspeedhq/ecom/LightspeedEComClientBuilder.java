package com.lightspeedhq.ecom;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import feign.Feign;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author stevensnoeijen
 */
public class LightspeedEComClientBuilder {

    private String cluster, language;
    private String apiKey, apiSecret;
    private boolean force = false;
    /**
     * Used for limiting the requests
     */
    private int minRemaining = -1, maxRequests = -1;

    public LightspeedEComClientBuilder() {

    }

    public LightspeedEComClientBuilder cluster(String cluster) {
        this.cluster = Objects.requireNonNull(cluster);
        return this;
    }

    public LightspeedEComClientBuilder language(String language) {
        this.language = Objects.requireNonNull(language);
        return this;
    }

    public LightspeedEComClientBuilder authorisation(String apiKey, String apiSecret) {
        this.apiKey = Objects.requireNonNull(apiKey);
        this.apiSecret = Objects.requireNonNull(apiSecret);
        return this;
    }

    /**
     *
     * @param force to force completion of the request, if the request rate is reached (rate is reset every 5min)
     *
     * @return builder
     */
    public LightspeedEComClientBuilder force(boolean force) {
        this.force = force;
        return this;
    }

    /**
     * The API is limited by a certain number of requests per 5 min (default 300).<br>
     * This can be used with {@link #force(boolean)} to force
     *
     * @param minRemaining leave this number of remaining by this client (for more important usage?), by default 0
     * @param maxRequests max number of requests that may be used by this client
     *
     * @return builder
     */
    public LightspeedEComClientBuilder limit(int minRemaining, int maxRequests) {
        if (minRemaining < 0) {
            throw new IllegalArgumentException("minRemaining >= 0");
        }
        if (maxRequests <= 0) {
            throw new IllegalArgumentException("maxRequests > 0");
        }

        this.minRemaining = minRemaining;
        this.maxRequests = maxRequests;
        return this;
    }

    public LightspeedEComClient build() {
        Objects.requireNonNull(cluster, "cluster missing");
        Objects.requireNonNull(language, "language missing");
        Objects.requireNonNull(apiKey, "apiKey missing");
        Objects.requireNonNull(apiSecret, "apiSecret missing");

        ObjectMapper om = new ObjectMapper();
        om.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //for using zoneddatetime JsonFormat
        om.registerModule(new JSR310Module());
        om.setTimeZone(TimeZone.getTimeZone("UTC"));

        Retryer retryer;
        if (force) {
            retryer = new Retryer.Default(0, TimeUnit.MINUTES.toMillis(5), 2);
        } else {
            retryer = new Retryer.Default();
        }

        Encoder encoder = new JacksonEncoder(om);
        Decoder decoder = new JacksonDecoder(om);
        ErrorDecoder errorDecoder;

        List<RequestInterceptor> requestInterceptors = new ArrayList<>();
        requestInterceptors.add(new BasicAuthRequestInterceptor(apiKey, apiSecret));

        if (minRemaining != -1 && maxRequests != -1) {
            Limiter limiter = new Limiter(minRemaining, maxRequests, force);
            //create for delay requests when limit is reached
            LimitRequestInterceptor limitRequestInterceptor = new LimitRequestInterceptor(limiter);
            requestInterceptors.add(limitRequestInterceptor);
            decoder = new LimitDecoder(decoder, limiter);//wrap decoder that the limiter can be updated with every response
            errorDecoder = new LightspeedEComErrorDecoder(limiter);
        } else {
            errorDecoder = new LightspeedEComErrorDecoder();
        }

        String url = createUrl(cluster, language);
        LightspeedEComClient client = Feign.builder()
                .requestInterceptors(requestInterceptors)
                .encoder(encoder)
                .decoder(decoder)
                .errorDecoder(errorDecoder)
                .retryer(retryer)
                .target(LightspeedEComClient.class, url);
        return client;
    }

    public static String createUrl(String cluster, String language) {
        return "https://" + cluster + "/" + language;
    }
}
