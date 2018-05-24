package com.soupthatisthick.util.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

import java.io.IOException;


public class JsonObjectMapper extends ObjectMapper {

    private JsonObjectMapper() {}

    public static JsonObjectMapper getInstance() { return getInstance(true, false); }

    public static JsonObjectMapper getInstance(boolean nonNull, boolean includeType) {

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();

        jsonObjectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        VisibilityChecker visibilityChecker = jsonObjectMapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE);

        jsonObjectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);

        if (nonNull) {
            jsonObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }

        if (includeType) {
            jsonObjectMapper.enableDefaultTyping();
        }

        return jsonObjectMapper;
    }

    public String toJson(Object object) { return toJson(object, false); }
    public String toJson(Object object, boolean prettyPrint) {
        if (object==null) {
            return null;
        }
        try {
            if (prettyPrint) {
                return writerWithDefaultPrettyPrinter().writeValueAsString(object);
            }
            return writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not serialize object of class " + object.getClass().getCanonicalName(), e);
        }
    }

    public <T> T toObject(String json, Class<T> objectType) {
        try {
            return readValue(json, objectType);
        } catch (IOException e) {
            throw new RuntimeException("Could not deserialize json to class " + objectType.getCanonicalName(), e);
        }
    }
}


