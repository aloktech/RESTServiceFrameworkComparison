package com.imos;

import java.io.IOException;
import java.util.logging.LogManager;

import io.helidon.config.Config;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerConfiguration;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.json.JsonSupport;

public final class HelidonApp {

    private static Routing createRouting() {
        return Routing.builder()
                .register(JsonSupport.get())
                .register("/rest", new HelidonRESTService())
                .build();
    }

    public static void main(final String[] args) throws IOException {
        startServer();
    }

    protected static WebServer startServer() throws IOException {

        // load logging configuration
        LogManager.getLogManager().readConfiguration(HelidonApp.class.getResourceAsStream("/logging.properties"));

        // By default this will pick up application.yaml from the classpath
        Config config = Config.create();

        // Get webserver config from the "server" section of application.yaml
        ServerConfiguration serverConfig =
                ServerConfiguration.fromConfig(config.get("server"));

        WebServer server = WebServer.create(serverConfig, createRouting());

        // Start the server and print some info.
        server.start().thenAccept(ws -> {
            System.out.println(
                    "WEB server is up! http://localhost:" + ws.port());
        });

        // Server threads are not demon. NO need to block. Just react.
        server.whenShutdown().thenRun(()
                -> System.out.println("WEB server is DOWN. Good bye!"));

        return server;
    }
}
