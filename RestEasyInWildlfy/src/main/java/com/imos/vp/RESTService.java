/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.vp;

import java.time.LocalTime;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.json.JSONObject;

/**
 *
 * @author pintu
 */
@Path("hello")
public class RESTService {

    @Path("resteasy")
    @GET
    public String getData() {
        JSONObject data = new JSONObject();
        data.put("data", "Hello World!");
        data.put("time", LocalTime.now());
        data.put("serviceProvider", "Resteasy");
        return data.toString();
    }
}
