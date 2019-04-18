/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest;

import com.imos.core.JSONResult;
import java.time.LocalTime;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author pintu
 */
@Path("rest")
public class RESTService {

    @Path("jersey")
    @GET
    public String getData() {
        String result = new JSONResult.JSONResultBuilder()
                .javaHome(System.getProperty("java.home"))
                .data("Hello World")
                .time(LocalTime.now().toString())
                .restService("Jersey")
                .server("Payara")
                .build()
                .stringify();
        return result;
    }
}
