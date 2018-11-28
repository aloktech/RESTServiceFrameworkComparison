package com.imos.rest;

import javax.json.Json;
import javax.json.JsonObject;

import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;
import java.time.LocalTime;

public class RESTController implements Service {

    @Override
    public final void update(final Routing.Rules rules) {
        rules
            .get("/helidon", this::getDefaultMessage);
    }

    private void getDefaultMessage(final ServerRequest request,
                                   final ServerResponse response) {

        JsonObject returnObject = Json.createObjectBuilder()
                .add("java-home", System.getProperty("java.home"))
                .add("time", LocalTime.now().toString())
                .add("data", "Hello World!")
                .add("rest-service", "Helidon")
                .add("server", "Netty")
                .build();
        response.send(returnObject);
    }
}