package com.imos.server;

import com.imos.core.JSONResult;
import java.time.LocalTime;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/rest/quarkus")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuarkusRESTService {

    @Path("")
    @GET
    public String list() {
        String result = new JSONResult.JSONResultBuilder()
                .javaHome(System.getProperty("java.home"))
                .data("Hello World")
                .time(LocalTime.now().toString())
                .restService("Quarkus")
                .server("Serverless")
                .build()
                .stringify();
        return result;
    }
}
