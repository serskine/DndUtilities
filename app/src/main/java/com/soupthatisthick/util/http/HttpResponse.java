package com.soupthatisthick.util.http;

/**
 * Created by Owner on 6/11/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class HttpResponse extends HttpMessage {

    @Override
    public void checkIfValid() throws Exception {
        super.checkIfValid();
        switch(getMethod()) {
            case HEAD:
                if (getBody()!=null) {
                    throw new Exception("Http responses that use method " + getMethod() + " must not have a body.");
                }
                break;
            case GET:
            case POST:
            case PUT:
            case DELETE:
            case CONNECT:
            case OPTIONS:
            case TRACE:
            case PATCH:
                if (getBody()==null) {
                    throw new RuntimeException("Http responses that use method " + getMethod() + " must have a non-null body.");
                }
                break;
            default:
        }
    }

}
