/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos;

import io.javalin.Javalin;
import java.time.LocalTime;
import org.json.JSONObject;

/**
 *
 * @author pintu
 */
public class JavalinRESTService {

    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7000);
        app.get("/rest/javalin", ctx -> {
            JSONObject data = new JSONObject();
            data.put("javaHome", System.getProperty("java.home"));
            data.put("data", "Hello World!");
            data.put("time", LocalTime.now());
            data.put("serviceProvider", "Javalin");
            ctx.result(data.toString());
        });
    }
}
