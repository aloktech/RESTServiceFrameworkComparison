/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos;

import com.mashape.unirest.http.Unirest;

/**
 *
 * @author pintu
 */
public class UniRESTClient implements ClientServiceProvider {

    @Override
    public String execute(String url) throws Exception {
        String data = "{}";
        try {
            data = Unirest.get(url)
                    .asString().getBody();
        } catch (Exception ex) {
            throw ex;
        }
        return data;
    }

    @Override
    public String getClientServiceName() {
        return "UniRest Client";
    }

    @Override
    public void config() {
    }

    @Override
    public void close() {
    }

}
