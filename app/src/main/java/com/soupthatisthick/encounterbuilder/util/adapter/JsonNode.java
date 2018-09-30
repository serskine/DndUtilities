package com.soupthatisthick.encounterbuilder.util.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Owner on 9/30/2018.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class JsonNode {

    public static enum Type {
        VALUE,
        JSON,
        ARRAY
    };



    private final Type type;
    private final Map<String, JsonNode> properties;
    private final Object value;

    public static final Builder builder() {
        return new JsonNode.Builder();
    }

    private JsonNode(Type type, Map<String, JsonNode> properties, Object value) {
        this.type = type;
        this.value = value;
        this.properties = properties;
    }

    public Object getValue() {
        return this.value;
    }

    public Map<String, JsonNode> getProperties() {
        return this.properties;
    }

    public static class Builder {
        private Map<String, JsonNode> properties;
        private Object value;

        public Builder() {
            this.properties = new HashMap<>();
            this.value = null;
        }

        public JsonNode build() {
            if (!properties.isEmpty()) {
                return new JsonNode(Type.JSON, properties, null);
            } else if (value==null || !value.getClass().isArray()) {
                return new JsonNode(Type.VALUE, properties, value);
            } else {
                return new JsonNode(Type.ARRAY, properties, value);
            }
        }

        public Builder values(Object... values) {
            if (values==null) {
                this.value = null;
            } if (values.length==1) {
                this.value = values[0];
            } else {
                this.value = values;
            }
            return this;
        }

        public Builder property(String name, Object value) {
            if (value != null && value instanceof JsonNode) {
                this.properties.put(
                    name,
                    (JsonNode) value
                );
            } else {
                this.properties.put(
                    name,
                    JsonNode.builder()
                        .values(value)
                        .build()
                );
            }
            return this;
        }

        public Builder property(String name, long value) {
            return property(name, new Long(value));
        }

        public Builder property(String name, int value) {
            return property(name, new Integer(value));
        }

        public Builder property(String name, short value) {
            return property(name, new Short(value));
        }

        public Builder property(String name, float value) {
            return property(name, new Float(value));
        }

        public Builder property(String name, double value) {
            return property(name, new Double(value));
        }

        public Builder property(String name, byte value) {
            return property(name, new Byte(value));
        }

        public Builder property(String name, boolean value) {
            return property(name, new Boolean(value));
        }

        public Builder property(String name, Character value) {
            return property(name, new Character(value));
        }


    }
}
