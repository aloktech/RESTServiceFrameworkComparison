/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import org.json.JSONObject;

/**
 *
 * @author alok.meher
 */
public class VertxRESTService extends AbstractVerticle {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("default");
    private static final String PORT = "port";

    @Override
    public void start(Future<Void> startFuture) throws Exception {

        String value = Optional.ofNullable(BUNDLE.getString(PORT)).orElse("8080");
        int port = Integer.parseInt(value);

        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());

        router.get("/testing").handler(handle -> {
            HttpServerResponse resp = handle.response();
            resp.end("Hello");
            resp.putHeader("Content-Type", "text/plain");
            handle.next();
        });
        router.get("/rest/vertx").handler(handle -> {
            HttpServerResponse resp = handle.response();
            JSONObject data = new JSONObject();
            data.put("java-home", System.getProperty("java.home"));
            data.put("data", "Hello World!");
            data.put("time", LocalTime.now());
            data.put("rest-service", "Vertx");
            data.put("server", "Netty");
            resp.end(data.toString());
            resp.putHeader("Content-Type", "application/json");
            handle.next();
        });
        vertx.createHttpServer().requestHandler(router).listen(port);
    }
}
