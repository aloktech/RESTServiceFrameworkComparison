/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest.old;

import java.time.LocalTime;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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
@RequestScoped
@Path("rest")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RESTEndPoint {

//    @Inject
    private RESTService service;

    public RESTEndPoint() {
        service = new RESTService();
    }

    @GET
    public String getMsg() {
        JSONObject json = new JSONObject();
        json.put("time", LocalTime.now().toString());
        return json.toString();
    }

    @Path("services")
    @GET
    public String getServiceList() {
        JSONObject json = new JSONObject();
        json.put("services", service.getAllServices());
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

    @DELETE
    @Path("deregisall")
    public Response serviceDeregistrationAll() {
        service.deregistrationOfAll();
        JSONObject json = new JSONObject();
        json.put("msg", "All registered service are removed");
        return Response.accepted().entity(json.toString()).build();
    }

    @Path("all/{iteration}")
    @GET
    public String getDataByIterration(@PathParam("iteration") int iteration) {

        return service.getDataByIteration(iteration);
    }

    @Path("all")
    @GET
    public String getData() {

        return service.getAllData().toString();
    }
}
