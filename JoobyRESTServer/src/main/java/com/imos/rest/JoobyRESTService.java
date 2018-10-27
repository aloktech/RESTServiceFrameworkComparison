/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest;

import java.time.LocalTime;
import org.jooby.Jooby;
import org.json.JSONObject;

/**
 *
 * @author pintu
 */
public class JoobyRESTService extends Jooby {

    {
        get("/rest/jooby", () -> {
            JSONObject data = new JSONObject();
            data.put("java-home", System.getProperty("java.home"));
            data.put("data", "Hello World!");
            data.put("time", LocalTime.now());
            data.put("rest-service", "Jooby");
            data.put("server", "Netty");
            return data.toString();
        });
    }

    public static void main(String[] args) {
        run(JoobyRESTService::new, args);
    }
}
