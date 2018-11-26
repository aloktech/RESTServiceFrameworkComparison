package com.imos;

import com.google.auto.service.AutoService;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author alok.meher
 */
@AutoService(ClientServiceProvider.class)
public class HttpClientJDK11 implements ClientServiceProvider {

    private HttpClient httpClient;

    @Override
    public void config() {
        httpClient = HttpClient.newBuilder()
                .build();
    }

    @Override
    public String execute(String url) throws Exception {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    @Override
    public String getClientServiceName() {
        return "HttpClient_JDK11";
    }

    @Override
    public void close() {
    }
}
