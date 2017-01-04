package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author stevensnoeijen
 */
@JsonRootName(value = "count")
@JsonSerialize(using = Count.CountSerializer.class)
@JsonDeserialize(using = Count.CountDeserializer.class)
public class Count implements Serializable {

    private static final long serialVersionUID = 2L;

    private int value;

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }

    public static class CountSerializer extends JsonSerializer<Count> {

        @Override
        public void serialize(Count value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            jgen.writeNumber(value.get());
        }

    }

    public static class CountDeserializer extends JsonDeserializer<Count> {

        @Override
        public Count deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            int value = jp.getIntValue();
            Count count = new Count();
            count.value = value;
            return count;
        }

    }

}
