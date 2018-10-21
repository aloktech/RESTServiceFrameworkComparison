/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.imos;

import javax.json.Json;
import javax.json.JsonObject;

import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;
import java.time.LocalTime;

public class HelidonRESTService implements Service {

    @Override
    public final void update(final Routing.Rules rules) {
        rules
            .get("/hello/helidon", this::getDefaultMessage);
    }

    private void getDefaultMessage(final ServerRequest request,
                                   final ServerResponse response) {

        JsonObject returnObject = Json.createObjectBuilder()
                .add("javaHome", System.getProperty("java.home"))
                .add("time", LocalTime.now().toString())
                .add("data", "Hello World!")
                .add("serviceProvider", "Helidon")
                .build();
        response.send(returnObject);
    }
}
