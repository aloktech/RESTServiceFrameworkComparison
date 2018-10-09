/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.vp.spark;

import java.time.LocalTime;
import org.json.JSONObject;
import spark.Spark;
import static spark.Spark.get;

/**
 *
 * @author pintu
 */
public class SparkProxyService {

    public static void main(String[] args) {

        get("/hello/spark", (req, res) -> {
            JSONObject data = new JSONObject();
            data.put("javaHome", System.getProperty("java.home"));
            data.put("data", "Hello World!");
            data.put("time", LocalTime.now());
            data.put("serviceProvider", "Spark");
            return data.toString();
        });
        get("/proxy-hello", (req, res) -> "Hello World!(Proxy) : " + LocalTime.now());
        get("/test-hello", (req, res) -> "Hello World!(Test) : " + LocalTime.now());

        Spark.post("/set-rule", (rep, res) -> "");
    }
}
