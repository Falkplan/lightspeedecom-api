package com.lightspeedhq.ecom.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author stevensnoeijen
 * @param <T> type of the object
 */
@AllArgsConstructor
public class WebhookEvent<T> {

    @Getter
    private Webhook.ItemGroup group;

    @Getter
    private Webhook.ItemAction action;

    /**
     * Object of the event.
     */
    @Getter
    private T object;
}
