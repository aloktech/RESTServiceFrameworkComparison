/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest;

import java.time.LocalTime;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pintu
 */
@RestController
public class RESTController {

    @RequestMapping("/rest/springboot")
    public String greeting() {
        JSONObject data = new JSONObject();
        data.put("java-home", System.getProperty("java.home"));
        data.put("data", "Hello World!");
        data.put("time", LocalTime.now());
        data.put("rest-service", "SpringBoot");
        data.put("server", "Tomcat");
        return data.toString();
    }
}