package com.imos.rest;

import com.imos.core.JSONResult;
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

        String result = new JSONResult.JSONResultBuilder()
                .javaHome(System.getProperty("java.home"))
                .data("Hello World")
                .time(LocalTime.now().toString())
                .restService("Helidon")
                .server("Netty")
                .build()
                .stringify();
        response.send(result);
    }
}
