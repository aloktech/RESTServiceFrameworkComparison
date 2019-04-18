/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest;


import com.imos.core.JSONResult;
import java.time.LocalTime;
import spark.Spark;
import static spark.Spark.get;

/**
 *
 * @author pintu
 */
public class SparkRESTService {

    public static void main(String[] args) {

        get("/rest/spark", (req, res) -> {
            String result = new JSONResult.JSONResultBuilder()
                    .javaHome(System.getProperty("java.home"))
                    .data("Hello World")
                    .time(LocalTime.now().toString())
                    .restService("Spark")
                    .server("Jetty")
                    .build()
                    .stringify();
            return result;
        });
        get("/proxy-hello", (req, res) -> "Hello World!(Proxy) : " + LocalTime.now());
        get("/test-hello", (req, res) -> "Hello World!(Test) : " + LocalTime.now());

        Spark.post("/set-rule", (rep, res) -> "");
    }
}
