/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.vp.micronaut;

/**
 *
 * @author pintu
 */
import io.micronaut.http.annotation.*;
import java.time.LocalTime;
import org.json.JSONObject;

@Controller("/hello")
public class HelloController {

    @Get("/micronaut")
    public String index() {
        JSONObject data = new JSONObject();
        data.put("data", "Hello World!");
        data.put("time", LocalTime.now());
        data.put("serviceProvider", "Micronaut");
        return data.toString();
    }
}
