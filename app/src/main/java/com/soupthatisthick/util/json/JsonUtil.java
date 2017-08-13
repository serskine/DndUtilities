package com.soupthatisthick.util.json;

import com.fasterxml.jackson.databind.JavaType;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.string.StringUtil;

import java.io.IOException;
import java.util.Collection;

public class JsonUtil {

    private static JsonObjectMapper includeAllMapper;
    private static JsonObjectMapper nonNullMapper;

    static {
        nonNullMapper = JsonObjectMapper.getInstance(true, false);
        includeAllMapper = JsonObjectMapper.getInstance(false, false);
    }

    private JsonUtil() {

    }

    public static String prettyPrintUglyJsonString(String uglyJsonString) {
        if (StringUtil.isNullOrEmpty(uglyJsonString)) {
            return "";
        }
        Object jsonObject = toObject(uglyJsonString, Object.class);
        return toJson(jsonObject, true, true);
    }

    public static String toJson(Object object) { return toJson(object, false);}

    public static String toJson(Object object, boolean prettyPrint) {
        return toJson(object, prettyPrint, false);
    }

    public static String toJson(Object object, boolean prettyPrint, boolean includeNullValues) {
        if (includeNullValues) {
            return includeAllMapper.toJson(object, prettyPrint);
        }
        return nonNullMapper.toJson(object, prettyPrint);
    }

    public static <T> T toObject(String json, Class<T> objectType) {
        return includeAllMapper.toObject(json, objectType);
    }

    public static <T extends Collection, E> T toObject(String json, Class<T> objectType, Class<E> genericType) {
        try {
            JavaType collectionType = includeAllMapper.getTypeFactory().constructCollectionType(objectType, genericType);
            return includeAllMapper.readValue(json, collectionType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
