/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest;

import com.imos.core.JSONResult;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.time.LocalTime;

/**
 * @author pintu
 */
@Path("rest")
public class RESTService {

    @Path("jersey")
    @GET
    public Response getData() {
        String result = JSONResult.builder()
                .javaHome(System.getProperty("java.home"))
                .data("Hello World")
                .time(LocalTime.now().toString())
                .restService("Jersey")
                .server("Tomcat")
                .build()
                .stringify();
        return Response.ok().entity(result).build();
    }
}
