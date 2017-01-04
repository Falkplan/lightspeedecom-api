package com.lightspeedhq.ecom.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

/**
 * Serialize a non-boolean value to false when the value is null.
 *
 * @author stevensnoeijen
 */
public class FalseNullSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        if (value == null) {
            jgen.writeBoolean(false);
        } else {
            jgen.writeObject(value);
        }
    }

}
