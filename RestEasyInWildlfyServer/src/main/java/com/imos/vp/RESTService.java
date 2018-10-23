<<<<<<< HEAD
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
@Path("rest")
public class RESTService {

    @Path("resteasy")
    @GET
    public String getData() {
        JSONObject data = new JSONObject();
        data.put("javaHome", System.getProperty("java.home"));
        data.put("data", "Hello World!");
        data.put("time", LocalTime.now());
        data.put("serviceProvider", "Resteasy");
        return data.toString();
    }
}
=======
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
@Path("rest")
public class RESTService {

    @Path("resteasy")
    @GET
    public String getData() {
        JSONObject data = new JSONObject();
        data.put("javaHome", System.getProperty("java.home"));
        data.put("data", "Hello World!");
        data.put("time", LocalTime.now());
        data.put("serviceProvider", "Resteasy");
        return data.toString();
    }
}
>>>>>>> 7d015baa7d9726a84b7333deaa5f2193d1489a92
