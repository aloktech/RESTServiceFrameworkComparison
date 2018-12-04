/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos;

import com.google.auto.service.AutoService;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author pintu
 */
@AutoService(ClientServiceProvider.class)
public class SpringRESTClient implements ClientServiceProvider {

    private RestTemplate restTemplate;

    @Override
    public void config() {
        restTemplate = new RestTemplate();
    }

    @Override
    public String execute(String url) throws Exception {
        return restTemplate.getForObject(url, String.class);
    }

    @Override
    public String getClientServiceName() {
        return "Spring Client";
    }

    @Override
    public void close() {
    }

}
