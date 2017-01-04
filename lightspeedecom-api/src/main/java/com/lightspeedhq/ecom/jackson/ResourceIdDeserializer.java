package com.lightspeedhq.ecom.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import java.io.IOException;
import java.util.TimeZone;

/**
 * Get id from resource or set to {@link VALUE_FALSE} if it is "false".
 *
 * @author stevensnoeijen
 */
public class ResourceIdDeserializer extends JsonDeserializer<Object> {

    private final ObjectMapper om;

    public static final int VALUE_FALSE = -1;

    public ResourceIdDeserializer() {
        om = new ObjectMapper();
        om.registerModule(new JSR310Module());
        om.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = om.readTree(jp);
        if (node.isBoolean()) {
            //is false = none set
            return VALUE_FALSE;
        }
        JsonNode resourceNode = node.get("resource");
        JsonNode idNode = resourceNode.get("id");
        int id = idNode.asInt();
        return id;
    }

}
