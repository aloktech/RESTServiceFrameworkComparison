/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jooq.lambda.Unchecked;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author pintu
 */
@Path("rest")
public class RESTService {

    private static final String JAVALIN_URL = "http://localhost:7000/hello/javalin";
    private static final String SPARK_URL = "http://localhost:4567/hello/spark";
    private static final String SPRINGBOOT_URL = "http://localhost:8080/hello/spring-boot";
    private static final String JERSEY_URL = "http://localhost:27643/JerseyInPayara/hello/jersey";
    private static final String RESTEASY_URL = "http://localhost:8089/RestEasyProxy/hello/resteasy";
    private static final String JERSEY_TOMCAT_URL = "http://localhost:8082/JerseyInTomcat/hello/tomcat";

    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();

    private final JSONArray data = new JSONArray();
    private int iteration = 1000;

    @Path("all/{iteration}")
    @GET
    public String getDataByIterration(@PathParam("iteration") int iteration) {
        this.iteration = iteration;

        executeScenarios();

        JsonObject json = Json.createObjectBuilder()
                .add("time", LocalTime.now().toString())
                .add("result", data.toString())
                .build();
        return json.toString();
    }

    @Path("all")
    @GET
    public String getData() {

        executeScenarios();

        JsonObject json = Json.createObjectBuilder()
                .add("time", LocalTime.now().toString())
                .add("result", data.toString())
                .build();
        return json.toString();
    }

    private void executeScenarios() {
        /*
        OKHttp
         */
        timeDiff("Jetty Server", "OKHttp", "Spark", SPARK_URL, Unchecked.function((String d) -> {
            return okhttpGETRequest(OK_HTTP_CLIENT, d);
        }).andThen(Unchecked.function(d -> d == null ? "" : d.string())));

        timeDiff("Jetty Server", "OKHttp", "Javalin", JAVALIN_URL, Unchecked.function((String u) -> {
            return okhttpGETRequest(OK_HTTP_CLIENT, u);
        }).andThen(Unchecked.function(d -> d == null ? "" : d.string())));

        timeDiff("Jetty Server", "OKHttp", "Spring Boot", SPRINGBOOT_URL, Unchecked.function((String u) -> {
            return okhttpGETRequest(OK_HTTP_CLIENT, u);
        }).andThen(Unchecked.function(d -> d == null ? "" : d.string())));

        timeDiff("Payara Server 5", "OKHttp", "Jersey", JERSEY_URL, Unchecked.function((String u) -> {
            return okhttpGETRequest(OK_HTTP_CLIENT, u);
        }).andThen(Unchecked.function(d -> d == null ? "" : d.string())));

        timeDiff("Tomcat", "OKHttp", "Jersey", JERSEY_TOMCAT_URL, Unchecked.function((String u) -> {
            return okhttpGETRequest(OK_HTTP_CLIENT, u);
        }).andThen(Unchecked.function(d -> d == null ? "" : d.string())));

        timeDiff("Wildfly Server 14", "OKHttp", "Resteasy", RESTEASY_URL, Unchecked.function((String u) -> {
            return okhttpGETRequest(OK_HTTP_CLIENT, u);
        }).andThen(Unchecked.function(d -> d == null ? "" : d.string())));

        /*
        UniTest
         */
        timeDiff("Jetty Server", "UniTest", "Spark", SPARK_URL, Unchecked.function(this::unirestJSONGETRequest));

        timeDiff("Jetty Server", "UniTest", "Javalin", JAVALIN_URL, Unchecked.function(this::unirestJSONGETRequest));

        timeDiff("Jetty Server", "UniTest", "Spring Boot", SPRINGBOOT_URL, Unchecked.function(this::unirestJSONGETRequest));

        timeDiff("Payara Server 5", "UniTest", "Jersey", JERSEY_URL, Unchecked.function(this::unirestJSONGETRequest));

        timeDiff("Tomcat", "UniTest", "Jersey", JERSEY_TOMCAT_URL, Unchecked.function(this::unirestJSONGETRequest));

        timeDiff("Wildfly Server 14", "UniTest", "Resteasy", RESTEASY_URL, Unchecked.function(this::unirestJSONGETRequest));

        /*
        Jersey
         */
        timeDiff("Jetty Server", "Jersey Client", "Spark", SPARK_URL, Unchecked.function(this::jerseyGetRequest));

        timeDiff("Jetty Server", "Jersey Client", "Javalin", JAVALIN_URL, Unchecked.function(this::jerseyGetRequest));

        timeDiff("Jetty Server", "Jersey Client", "Spring Boot", SPRINGBOOT_URL, Unchecked.function(this::jerseyGetRequest));

        timeDiff("Payara Server 5", "Jersey Client", "Jersey", JERSEY_URL, Unchecked.function(this::jerseyGetRequest));

        timeDiff("Tomcat", "Jersey Client", "Jersey", JERSEY_TOMCAT_URL, Unchecked.function(this::jerseyGetRequest));

        timeDiff("Wildfly Server 14", "Jersey Client", "Resteasy", RESTEASY_URL, Unchecked.function(this::jerseyGetRequest));
    }

    private ResponseBody okhttpGETRequest(OkHttpClient client, String url) throws IOException {
        Request req = new Request.Builder()
                .url(url)
                .build();

        Response res = client.newCall(req).execute();
        return res.body();
    }

    private String jerseyGetRequest(String u) {
        Client jerseyClient = ClientBuilder.newClient();
        WebTarget target = jerseyClient.target(u);
        javax.ws.rs.core.Response response1 = target.request().get();
        return response1.readEntity(String.class);
    }

    private String unirestStringGETRequest(String d) throws UnirestException {
        return Unirest.get(d)
                .asString().getBody();
    }

    private String unirestJSONGETRequest(String d) throws UnirestException {
        return Unirest.get(d)
                .asJson().getBody().toString();
    }

    private <I, O> void timeDiff(String serverName, String clientTitle, String serverTitle, I url, Function<I, O> func) {
        JSONObject result = new JSONObject();
        int count = iteration;
        try {

//        for (int p = 100; p <= 1000; p *= 10) {
            count = iteration == 0 ? 1000 : iteration;
            result.put("start-time", System.nanoTime());
            long begin = System.nanoTime();
            long[] times = new long[count];
            for (int i = 0; i < count; i++) {
                long startTime = System.nanoTime();
                func.apply(url);
                times[i] = System.nanoTime() - startTime;
            }
            long end = System.nanoTime();
            result.put("end-time", System.nanoTime());
            double diffAvg = Arrays.stream(times).average().getAsDouble();
            result.put("diff-time", end - begin);
            result.put("avg-diff-time", diffAvg);
            result.put("iteration", count);
            result.put("server-service-provider", serverTitle);
            result.put("client-service-provider", clientTitle);
            result.put("server", serverName);
            data.put(result);
        } catch (Exception e) {
            Throwable th = e;
            JSONArray errorArray = new JSONArray();
            while(Objects.nonNull(th)) {
                JSONObject errorResult = new JSONObject();
                errorResult.put("error-msg", th.getMessage());
                errorResult.put("error-class", th.getClass().getSimpleName());
                errorArray.put(errorResult);
                th = th.getCause();
            }
            JSONObject error = new JSONObject();
            error.put("errors", errorArray);
            data.put(error);
        }
//        }
    }
}
