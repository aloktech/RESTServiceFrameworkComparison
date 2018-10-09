/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.vp.springboot;

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

    private JSONObject data = new JSONObject();

    {
        data.put("data", "Hello World!");
        data.put("time", LocalTime.now());
        data.put("serviceProvider", "SpringBoot");
    }

    @RequestMapping("/hello/spring-boot")
    public String greeting() {
        return data.toString();
    }
}
