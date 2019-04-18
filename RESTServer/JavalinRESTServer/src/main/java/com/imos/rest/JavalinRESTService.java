/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest;

import com.imos.core.JSONResult;
import io.javalin.Javalin;
import java.time.LocalTime;

/**
 *
 * @author pintu
 */
public class JavalinRESTService {

    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7000);
        app.get("/rest/javalin", ctx -> {
            String result = new JSONResult.JSONResultBuilder()
                    .javaHome(System.getProperty("java.home"))
                    .data("Hello World")
                    .time(LocalTime.now().toString())
                    .restService("Javalin")
                    .server("Jetty")
                    .build()
                    .stringify();
            ctx.result(result);
        });
    }
}
