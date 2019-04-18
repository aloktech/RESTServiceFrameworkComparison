/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest;

import com.google.inject.Singleton;
import io.sinistral.proteus.server.ServerRequest;
import io.sinistral.proteus.server.ServerResponse;
import static io.sinistral.proteus.server.ServerResponse.response;
import java.nio.ByteBuffer;
import java.time.LocalTime;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.imos.core.JSONResult;
/**
 *
 * @author pintu
 */
@Path("/rest")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.WILDCARD})
@Singleton
public class RESTController {

    @GET
    @Path("/proteus")
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ServerResponse<ByteBuffer> helloWorldText(ServerRequest request) {
        String result = new JSONResult.JSONResultBuilder()
                .javaHome(System.getProperty("java.home"))
                .data("Hello World")
                .time(LocalTime.now().toString())
                .restService("Proteus")
                .server("Undertow")
                .build()
                .stringify();
        return response(result);
    }
}
