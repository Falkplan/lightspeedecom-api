package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.*;
import com.lightspeedhq.ecom.LightspeedEComClient;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * Retrieve and manage all the webhooks from this shop.
 * Webhooks allow you to subscribe to certain events and we'll notify you as soon as it occurs.
 * This is done by requesting a url that you provide us with.
 * To learn more about how webhooks work <a href="http://developers.seoshop.com/api/tutorials/webhooks">see the WebhookEdit tutorial</a>.
 *
 * @see <a href="http://developers.seoshop.com/api/resources/webhook">http://developers.seoshop.com/api/resources/webhook</a>
 * @see <a href="http://developers.seoshop.com/api/tutorials/webhooks">http://developers.seoshop.com/api/tutorials/webhooks</a>
 * @author stevensnoeijen
 */
@JsonRootName("webhook")
public class Webhook implements Serializable {

    private static final long serialVersionUID = 2L;

    @JsonRootName("webhooks")
    public static class List extends ArrayList<Webhook> {
    }

    public static enum ItemGroup {
        CUSTOMERS,
        ORDERS,
        INVOICES,
        SHIPMENTS,
        PRODUCTS,
        VARIANTS,
        QUOTES,
        REVIEWS,
        RETURNS,
        TICKETS,
        SUBSCRIPTIONS,
        CONTACTS;

        @JsonValue
        public String toJson() {
            return this.name().toLowerCase();
        }

        @JsonCreator
        public static ItemGroup fromJson(String value) {
            return Enum.valueOf(ItemGroup.class, value.toUpperCase());
        }
    }

    public static enum ItemAction {
        @JsonProperty("*")
        ALL,
        CREATED,
        UPDATED,
        DELETED;

        @JsonValue
        public String toJson() {
            return this.name().toLowerCase();
        }

        @JsonCreator
        public static ItemAction fromJson(String value) {
            return Enum.valueOf(ItemAction.class, value.toUpperCase());
        }
    }

    public static enum Format {
        JSON, XML;

        @JsonValue
        public String toJson() {
            return this.name().toLowerCase();
        }

        @JsonCreator
        public static Format fromJson(String value) {
            return Enum.valueOf(Format.class, value.toUpperCase());
        }
    }

    public static class EventDescriptor implements Serializable {

        private static final long serialVersionUID = 1L;

        @Getter
        private ItemGroup group;

        @Getter
        private ItemAction action;

        /**
         *
         * @param group non null
         * @param action non null and not {@link Webhook.ItemAction.ALL}
         *
         * @return x-event value
         */
        public static String toString(ItemGroup group, ItemAction action) {
            Objects.requireNonNull(group);
            Objects.requireNonNull(action);
            if (action == ItemAction.ALL) {
                throw new IllegalArgumentException("Can not event 'all'");
            }
            return group.name().toLowerCase() + "/" + action.toString().toLowerCase();
        }

        /**
         * Parses string to event
         *
         * @param value that contain "group/action"
         *
         * @return event
         */
        public static EventDescriptor fromString(String value) {
            EventDescriptor eventDescriptor = new EventDescriptor();

            String[] args = value.split("/");
            eventDescriptor.group = ItemGroup.fromJson(args[0]);
            eventDescriptor.action = ItemAction.fromJson(args[1]);

            return eventDescriptor;
        }

        @Override
        public String toString() {
            return toString(this.group, this.action);
        }
    }

    /**
     * The unique numeric identifier for the webhook.<br/>
     * Example: <code>{"id": "{id}"}</code>
     */
    @Getter
    private int id;

    /**
     * The date and time when the webhook was created.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"createdAt": "2013-09-24T21:48:17+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime createdAt;

    /**
     * The date and time when the webhook was last updated.<br/>
     * (format: ISO-8601)<br/>
     * Example: <code>{"updatedAt": "2013-09-24T22:02:24+02:00"}</code>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LightspeedEComClient.DATETIME_FORMAT)
    @Getter
    private ZonedDateTime updatedAt;

    @Getter
    @Setter
    @JsonProperty("isActive")
    private boolean isActive;

    /**
     * The item group. Available groups: {@link ItemGroup}.<br/>
     * Example: <code>{"itemGroup": "customers"}</code>
     */
    @Getter
    @Setter
    private ItemGroup itemGroup;

    /**
     * The item action.<br/>
     * Example: <code>{"itemAction": "*|created|updated|deleted"}</code>
     */
    @Getter
    @Setter
    private ItemAction itemAction;

    /**
     * Two letter code representing the language.<br/>
     * (format: ISO 639-1)<br/>
     * Example: <code>{"language": "NL"}</code>
     */
    @Getter
    @Setter
    private Language language;

    /**
     * Format of the webhook payload.<br/>
     * Example: <code>{"format": "json|xml"}</code>
     */
    @Getter
    @Setter
    private Format format = Format.JSON;

    /**
     * WebhookEdit URL that we'll call when the event is triggered.<br/>
     * Example: <code>{"address": "http://requestb.in/17hcl441"}</code>
     */
    @Getter
    @Setter
    private String address;
}
