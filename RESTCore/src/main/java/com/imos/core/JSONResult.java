/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.json.JSONObject;

/**
 * @author p
 */
@Builder
@AllArgsConstructor
public class JSONResult {

    private final String javaHome;
    private final String data;
    private final String time;
    private final String restService;
    private final String server;

    public String stringify() {
        JSONObject json = new JSONObject();
        json.put("java-home", javaHome);
        json.put("data", data);
        json.put("time", time);
        json.put("rest-service", restService);
        json.put("server", server);
        return json.toString(4);
    }
}
