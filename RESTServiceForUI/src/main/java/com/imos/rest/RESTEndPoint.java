/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest;

import java.time.LocalTime;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 *
 * @author pintu
 */
@Path("rest")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RESTEndPoint {

    @Inject
    private RESTService service;

    @GET
    public String getMsg() {

        JSONObject json = new JSONObject();
        json.put("time", LocalTime.now().toString());
        return json.toString();
    }

    @POST
    @Path("regis")
    public Response serviceRegistration(String data) {
        JSONObject json = service.registration(data);
        return Response.accepted().entity(json.toString()).build();
    }

    @DELETE
    @Path("deregis")
    public Response serviceDeregistration(String data) {
        JSONObject json = service.deregistration(data);
        return Response.accepted().entity(json.toString()).build();
    }

    @Path("all/{iteration}")
    @GET
    public String getDataByIterration(@PathParam("iteration") int iteration) {

        JsonObject json = service.getDataByIterration(iteration);
        return json.toString();
    }

    @Path("all")
    @GET
    public String getData() {

        JSONObject json = service.getAllData();
        return json.toString();
    }
}
