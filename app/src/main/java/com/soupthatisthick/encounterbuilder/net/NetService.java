package com.soupthatisthick.encounterbuilder.net;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.http.HttpStatus;
import com.soupthatisthick.util.json.JsonUtil;

import org.apache.http.client.methods.HttpPost;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by stuart.erskine on 2018-03-04.
 */

public abstract class NetService {

    protected HttpURLConnection connection;
    protected final URL url;
    protected final String username, password;


    public NetService(URL url, final String username, final String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        try {
            this.connection = (HttpURLConnection) url.openConnection();
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }
    }

    protected GetRequest get() {
        return Unirest.get(url.toString()).basicAuth(username, password);
    }

    protected HttpRequestWithBody post() {
        return Unirest.post(url.toString()).basicAuth(username, password);
    }


    public void sendMessage() {

        try {
            HttpResponse<String> response = Unirest.head("http://www.google.ca").asString();
            Logger.info(response.getBody());
        } catch (UnirestException e) {
            Logger.error(e.getMessage(), e);
        }
    }

    public HttpStatus sendRequest(final Object request) throws UnirestException {
        final String body = JsonUtil.toJson(request, true);
        HttpResponse<String> response = post().body(JsonUtil.toJson(request, true)).asString();
        return HttpStatus.parse(response.getStatus());
    }

}
