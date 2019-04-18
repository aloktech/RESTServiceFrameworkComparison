package com.imos.rest;


import com.imos.core.JSONResult;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class VertxRESTService extends AbstractVerticle {

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("hh:mm:ss.SSS a dd/MMM/yyyy");

    public static void main(String[] args) {
        Runner.runExample(VertxRESTService.class);
    }

    @Override
    public void start() {
        Router router = Router.router(vertx);

        router.get("/rest/vertx")
                .handler(cxt -> {
                    HttpServerResponse response = cxt.response();
                    response.putHeader("content-type", "application/json");
                    
                    String result = new JSONResult.JSONResultBuilder()
                            .javaHome(System.getProperty("java.home"))
                            .data("Hello World")
                            .time(LocalTime.now().toString())
                            .restService("Vertx")
                            .server("Netty")
                            .build()
                            .stringify();
                    response.end(result);
                });

        router.route()
                .handler(cxt -> {

                    // This handler gets called for each request that arrives on the server
                    HttpServerResponse response = cxt.response();
                    response.putHeader("content-type", "text/plain");

                    // Write to the response and end it
                    response.end("Hello World! " + LocalDateTime.now().format(dateFormatter));
                });

        vertx.createHttpServer().requestHandler(router).listen(8084);
    }

}
