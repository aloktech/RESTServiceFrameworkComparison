/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest;

import com.imos.core.JSONResult;
import org.jooby.Jooby;

import java.time.LocalTime;

/**
 * @author pintu
 */
public class JoobyRESTService extends Jooby {

    {
        get("/rest/jooby", () -> JSONResult.builder()
                .javaHome(System.getProperty("java.home"))
                .data("Hello World")
                .time(LocalTime.now().toString())
                .restService("Jooby")
                .server("Netty")
                .build()
                .stringify());
    }

    public static void main(String[] args) {
        run(JoobyRESTService::new, args);
    }
}
