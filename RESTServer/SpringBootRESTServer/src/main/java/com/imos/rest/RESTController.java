/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest;


import com.imos.core.JSONResult;
import java.time.LocalTime;
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
        String result = new JSONResult.JSONResultBuilder()
                .javaHome(System.getProperty("java.home"))
                .data("Hello World")
                .time(LocalTime.now().toString())
                .restService("SpringBoot")
                .server("Tomcat")
                .build()
                .stringify();
        return result;
    }
}
