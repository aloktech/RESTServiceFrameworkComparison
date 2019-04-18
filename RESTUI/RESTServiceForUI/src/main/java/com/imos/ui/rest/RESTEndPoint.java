/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.ui.rest;

import io.javalin.Javalin;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author pintu
 */
public class RESTEndPoint {

    private static final Logger LOG = LogManager.getLogger(RESTEndPoint.class);

    public static void main(String[] args) {

        LOG.info("begin");

        Javalin app = Javalin.create().enableCorsForAllOrigins().start(7000);

        LOG.info("app begin");

        RESTService service = new RESTService();

        LOG.info("service started");

        app.get("/rest", ctx -> {
            JSONObject json = new JSONObject();
            json.put("time", LocalTime.now().toString());

            ctx.status(200);
            ctx.result(json.toString());
        });
        app.get("/rest/services", ctx -> {
            JSONObject json = new JSONObject();
            json.put("services", service.getAllServices());
            json.put("time", LocalTime.now().toString());

            ctx.status(200);
            ctx.result(json.toString());
        });
        app.get("/rest/all", ctx -> {
            ctx.status(200);
            ctx.result(service.getAllData().toString());
        });
        app.get("/rest/all/:iteration", ctx -> {
            int iteration = Integer.parseInt(Optional.ofNullable(ctx.pathParam("iteration")).orElse("0"));
            ctx.status(200);
            ctx.result(service.getDataByIteration(iteration));
        });
        app.post("/rest/regis", ctx -> {
            HttpServletRequest req = ctx.req;
            ServletInputStream inputStream = req.getInputStream();
            String data = new JSONObject().toString();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                data = reader.lines().collect(Collectors.joining());
            } catch (Exception e) {

            }
            JSONObject json = service.registration(data);
            ctx.result(json.toString());
            ctx.status(201);
        });
        app.delete("/rest/deregis", ctx -> {
            HttpServletRequest req = ctx.req;
            ServletInputStream inputStream = req.getInputStream();
            String data = new JSONObject().toString();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                data = reader.lines().collect(Collectors.joining());
            } catch (Exception e) {

            }
            JSONObject json = service.deregistration(data);
            ctx.result(json.toString());
            ctx.status(200);
        });
        app.delete("/rest/deregisall", ctx -> {
            service.deregistrationOfAll();
            JSONObject json = new JSONObject();
            json.put("msg", "All registered service are removed");
            ctx.result(json.toString());
            ctx.status(200);
        });

        LOG.info("app started");
    }
}
