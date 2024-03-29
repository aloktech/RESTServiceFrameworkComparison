/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos;

import com.google.auto.service.AutoService;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author pintu
 */
@Log4j2
@AutoService(HttpClientServiceProvider.class)
public class JerseyHttpClient implements HttpClientServiceProvider {

    private Client jerseyClient;

    @Override
    public void config() {
        jerseyClient = ClientBuilder.newClient();
    }

    @Override
    public String execute(String url) {
        String data;
        try {
            WebTarget target = jerseyClient.target(url);
            Response response = target.request(MediaType.APPLICATION_JSON).get();
            data = response.readEntity(String.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
        return data;
    }

    @Override
    public String getClientServiceName() {
        return "Jersey Client";
    }

    @Override
    public void close() {
        jerseyClient.close();
    }

}
