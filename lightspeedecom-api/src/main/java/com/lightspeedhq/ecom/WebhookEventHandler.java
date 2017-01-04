package com.lightspeedhq.ecom;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.lightspeedhq.ecom.domain.Webhook;
import com.lightspeedhq.ecom.domain.WebhookEvent;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import lombok.SneakyThrows;
import lombok.Synchronized;
import org.apache.commons.collections.map.MultiValueMap;

/**
 *
 * @author stevensnoeijen
 */
public class WebhookEventHandler {

    private ObjectMapper om;

    private MultiValueMap listeners = MultiValueMap.decorate(new HashMap<String, Consumer<WebhookEvent>>());

    public WebhookEventHandler() {
        om = new ObjectMapper();
        om.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        //for using zoneddatetime JsonFormat
        om.registerModule(new JSR310Module());
        om.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @SneakyThrows(IOException.class)
    public <T> T parse(String body, Class<T> type) {
        T object = om.readValue(body, type);
        return object;
    }

    /**
     *
     * @param <T> type of the object
     * @param body that contains the object that will be {@link #parse(java.lang.String, java.lang.Class) }
     * @param type of the object
     * @param headers that contain event info
     */
    public <T> void handle(String body, Class<T> type, Map<String, List<String>> headers) {
        T object = parse(body, type);

        String eventString = headers.get(LightspeedEComClient.HEADER_EVENT).get(0);
        Webhook.EventDescriptor eventDescriptor = Webhook.EventDescriptor.fromString(eventString);

        WebhookEvent<T> event = new WebhookEvent<>(eventDescriptor.getGroup(), eventDescriptor.getAction(), object);
        trigger(event);
    }

    @SuppressWarnings("unchecked")
    @Synchronized
    public <T> void addListener(Webhook.EventDescriptor eventDescriptor, Consumer<WebhookEvent<T>> listener) {
        this.listeners.put(eventDescriptor.toString(), (Consumer) listener);
    }

    @Synchronized
    public void clearListeners() {
        this.listeners.clear();
    }

    @Synchronized
    protected <T> void trigger(WebhookEvent<T> event) {
        @SuppressWarnings("unchecked")
        Collection<Consumer<WebhookEvent>> listeners = this.listeners.getCollection(Webhook.EventDescriptor.toString(event.getGroup(), event.getAction()));
        if (listeners != null) {
            listeners.forEach(listener -> listener.accept(event));
        }
    }
}
