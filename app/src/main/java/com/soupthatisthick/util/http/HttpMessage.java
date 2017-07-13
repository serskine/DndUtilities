package com.soupthatisthick.util.http;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Owner on 6/11/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class HttpMessage {
    private URL url = null;
    public final Map<String, String> args = new HashMap<>();
    private HttpMethod method = null;
    private HttpStatus status = null;
    private byte[] body = null;

    /**
     * This will tell you if the message is valid but will not tell you why it is not
     * @return true if valid and false if it is not
     */
    public final boolean isValid() {
        try {
            checkIfValid();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This will throw an exception with context if the  message is not valid.
     */
    @CallSuper
    public void checkIfValid() throws Exception {
        if (getUrl() == null) {
            throw new Exception("All http messages must have a non-null URL assigned.");
        }
        if (getMethod()==null) {
            throw new Exception("All http messages must have a non-null method set.");
        }
    }


    public final HttpMethod getMethod()
    {
        return this.method;
    }

    public final void setMethod(@NonNull HttpMethod method)
    {
        this.method = method;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(@Nullable byte[] body) {
        this.body = body;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(@NonNull HttpStatus status) {
        this.status = status;
    }

    public URL getUrl() {
        return this.url;
    }

    public void setUrl(@NonNull URL url) {
        this.url = url;
    }

    /**
     * This will attempt to send the data
     * @throws Exception
     */
    public JSONObject send() throws Exception {
        // Initialize the connection
        HttpURLConnection connection = null;
        JSONObject response = null;

        try {
            checkIfValid();
            connection = (HttpURLConnection) getUrl().openConnection();
            connection.setRequestMethod(getMethod().toString());
            connection.setDoOutput(true);

            OutputStream outputPost = new BufferedOutputStream(connection.getOutputStream());
            //        writeStream(outputPost);    // TODO: Implement
            outputPost.flush();
            outputPost.close();


            // Gets the returned respnse as a JSONObject
            try {
                response = getJsonFromURL(getUrl());
            } catch (Exception e) {
                response = null;
            }

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return response;
    }

    /**
     * This will get the response back from the given URL
     * @param url
     * @return a JSON object
     * @throws Exception
     */
    private static final JSONObject getJsonFromURL(URL url) throws Exception {
        BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(url.openStream()));

        String jsonString;

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line+"\n");
        }
        bufferedReader.close();

        jsonString = stringBuilder.toString();
        return new JSONObject(jsonString);
    }



}
