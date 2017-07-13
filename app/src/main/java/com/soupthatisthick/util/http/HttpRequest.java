package com.soupthatisthick.util.http;

/**
 * Created by Owner on 6/11/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class HttpRequest extends HttpMessage {

    @Override
    public void checkIfValid() throws Exception {
        super.checkIfValid();

        switch(getMethod()) {

            case GET:
            case HEAD:
            case DELETE:
            case TRACE:
                if (getBody()!=null) {
                    throw new Exception("Http requests using method " + getMethod() + " is not allowed to have a body.");
                }
                break;
            case POST:
            case PUT:
            case CONNECT:
            case PATCH:
                if (getBody()==null) {
                    throw new Exception("Http requests using method " + getMethod() + " must have a non-null body defined for them.");
                }
                break;
            case OPTIONS:
            default:
                // Do nothing. this is OK.
        }
    }
}
