package com.imos.server;

import com.imos.core.JSONResult;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalTime;

@Path("/rest/quarkus")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuarkusRESTService {

    @Path("")
    @GET
    public String list() {
        return JSONResult.builder()
                .javaHome(System.getProperty("java.home"))
                .data("Hello World")
                .time(LocalTime.now().toString())
                .restService("Quarkus")
                .server("Undertow")
                .build()
                .stringify();
    }
}
