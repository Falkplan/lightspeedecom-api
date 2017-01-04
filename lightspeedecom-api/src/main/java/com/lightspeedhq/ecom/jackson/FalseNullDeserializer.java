package com.lightspeedhq.ecom.jackson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import java.io.IOException;
import java.util.TimeZone;

/**
 * De-serialize a non-boolean value to null when it is false.
 *
 * @author stevensnoeijen
 */
public class FalseNullDeserializer extends JsonDeserializer<Object> implements ContextualDeserializer {

    private final ObjectMapper om;

    private JavaType type;

    public FalseNullDeserializer() {
        om = new ObjectMapper();
        om.registerModule(new JSR310Module());
        om.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        type = property.getType();
        return this;
    }

    @Override
    public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        try {
            //try boolean
            jp.getBooleanValue();//false
            return null;
        } catch (JsonParseException ex) {
            //if its an object it will throw
            //read with default deserializer
            return om.readValue(jp, type);
        }
    }
}
