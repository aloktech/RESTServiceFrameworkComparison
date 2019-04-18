/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest;

import com.imos.core.JSONResult;
import java.time.LocalTime;
import org.jooby.Jooby;

/**
 *
 * @author pintu
 */
public class JoobyRESTService extends Jooby {

    {
        get("/rest/jooby", () -> {
            String result = new JSONResult.JSONResultBuilder()
                    .javaHome(System.getProperty("java.home"))
                    .data("Hello World")
                    .time(LocalTime.now().toString())
                    .restService("Jooby")
                    .server("Netty")
                    .build()
                    .stringify();
            return result;
        });
    }

    public static void main(String[] args) {
        run(JoobyRESTService::new, args);
    }
}
