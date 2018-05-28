package com.soupthatisthick.encounterbuilder.util;

import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.InstrumentationTest;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.http.HttpMethod;
import com.soupthatisthick.util.http.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

/**
 * Created by Owner on 6/11/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class HttpTest extends InstrumentationTest {

    public static final String GET_SERVER = "http://httpbin.org/get";
    public static final String POST_SERVER = "http://httpbin.org/post";

    public static final byte[] BODY = "The quick brown fox jumped over the lazy dog.".getBytes();

    private static final Map<String, String> createParams()
    {
        Map<String,String> params = new HashMap<>();
        for(int i=0; i<10; i++)
        {
            params.put("arg_" + i, "value_" + i);
        }
        return params;
    }

    @Test
    public void testSendGet()
    {
//        JSONObject response = null;
//        try {
//            response = Http.getMessage(GET_SERVER, null);
//        } catch (Exception e) {
//            Logger.error("Failed to send", e);
//        }
//        logResponse(response);
    }

    @Test
    public void testSendGetParams()
    {
//        Map<String, String> params = createParams();
//        JSONObject response = null;
//        try {
//            response = Http.getMessage(GET_SERVER, params);
//        } catch (Exception e) {
//            Logger.error("Failed to send", e);
//        }
//        logResponse(response);
    }



    @Test
    public void testSendPost()
    {
        JSONObject response = null;
        try {
            HttpRequest request = new HttpRequest();
            URL url = new URL(POST_SERVER);

            request.setUrl(url);
            request.setMethod(HttpMethod.POST);

            assertNotNull(request.getUrl());

            request.setBody(BODY);
            response = request.send();
        } catch (Exception e) {
            Logger.error("Failed to send", e);
        }
        logResponse(response);
    }


    @Test
    public void testSendPostParams()
    {
//        Map<String,String> params = createParams();
//        JSONObject response = null;
//        try {
//             response = Http.postMessage(POST_SERVER, params, "The quick brown fox jumped over the lazy dog.".getBytes());
//        } catch (Exception e) {
//            Logger.error("Failed to send", e);
//        }
//        logResponse(response);
    }


    @Override
    protected void onSetup() {
        // Do nothing
    }

    @Override
    protected void onTeardown() {

    }

    /**
     * Logs the response to the console.
     * @param response
     */
    private void logResponse(@Nullable JSONObject response) {

        if (response==null) {
            Logger.info("Response is null");
        } else {
            try {
                Logger.info(response.toString(4));
            } catch (JSONException e) {
                fail(e.getMessage());
            }
        }
    }
}
