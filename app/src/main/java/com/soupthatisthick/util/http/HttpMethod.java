package com.soupthatisthick.util.http;

/**
 * Created by Owner on 6/11/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public enum HttpMethod {
    GET("GET",          false, true, true, true, true),
    HEAD("HEAD",        false, false, true, true, true),
    POST("POST",        true, true, false, false, true),
    PUT("PUT",          true, true, false, true, false),
    DELETE("DELETE",    false, true, false, true, false),
    CONNECT("CONNECT",  true, true, false, false, false),
    OPTIONS("OPTIONS",  true, true, true, true, false), // HttpRequest has body is actually optional
    TRACE("TRACE",      false, true, true, true, false),
    PATCH("PATCH",      true, true, false, false, true);

    private final String text;
    private final boolean requestHasBody;
    private final boolean responseHasBody;
    private final boolean safe;
    private final boolean idempotent;
    private final boolean cacheable;

    private HttpMethod(
        String text,
        boolean requestHasBody,
        boolean responseHasBody,
        boolean safe,
        boolean idempotent,
        boolean cacheable)
    {
        this.text = text;
        this.requestHasBody = requestHasBody;
        this.responseHasBody = responseHasBody;
        this.safe = safe;
        this.idempotent = idempotent;
        this.cacheable = cacheable;
    }

    @Override
    public final String toString() {
        return text;
    }

    public boolean isRequestHasBody() {
        return requestHasBody;
    }

    public boolean isResponseHasBody() {
        return responseHasBody;
    }

    public boolean isSafe() {
        return safe;
    }

    public boolean isIdempotent() {
        return idempotent;
    }

    public boolean isCacheable() {
        return cacheable;
    }
}
