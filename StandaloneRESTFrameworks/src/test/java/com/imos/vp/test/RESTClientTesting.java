/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.vp.test;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.function.Function;
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
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author pintu
 */
public class RESTClientTesting {

    private static final String JAVALIN_URL = "http://localhost:7000/hello/javalin";
    private static final String SPARK_URL = "http://localhost:4567/hello/spark";
    private static final String SPRINGBOOT_URL = "http://localhost:8080/hello/spring-boot";
    private static final String JERSEY_URL = "http://localhost:27643/JerseyProxy/hello/jersey";
    private static final String RESTEASY_URL = "http://localhost:8089/RestEasyProxy/hello/resteasy";

    static JSONArray data = new JSONArray();

    @AfterAll
    public static void shutdown() {
        try {
            JSONObject results = new JSONObject();
            results.put("results", data);
            String alias = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MMM_yyyy-HH_mm"));
            Files.write(Paths.get("result-" + alias + ".json"), results.toString().getBytes(), StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void jerseyClientTesting() {
        System.out.println("## Jersey Client");
        timeDiff("Jetty Server", "Jersey Client", "Spark", SPARK_URL, Unchecked.function(this::jerseyGetRequest));

        timeDiff("Jetty Server", "Jersey Client", "Javalin", JAVALIN_URL, Unchecked.function(this::jerseyGetRequest));

        timeDiff("Jetty Server", "Jersey Client", "Spring Boot", SPRINGBOOT_URL, Unchecked.function(this::jerseyGetRequest));
//
        timeDiff("Payara Server 5", "Jersey Client", "Jersey", JERSEY_URL, Unchecked.function(this::jerseyGetRequest));

        timeDiff("Wildfly Server 14", "Jersey Client", "Resteasy", RESTEASY_URL, Unchecked.function(this::jerseyGetRequest));
    }

    @Test
    public void unirestTesting() {
        System.out.println("## UniTest");
//        timeDiff("Jetty Server", "UniTest", "Spark String", SPARK_URL, Unchecked.function(this::unirestStringGETRequest));

        timeDiff("Jetty Server", "UniTest", "Spark", SPARK_URL, Unchecked.function(this::unirestJSONGETRequest));

        timeDiff("Jetty Server", "UniTest", "Javalin", JAVALIN_URL, Unchecked.function(this::unirestJSONGETRequest));

        timeDiff("Jetty Server", "UniTest", "Spring Boot", SPRINGBOOT_URL, Unchecked.function(this::unirestJSONGETRequest));

        timeDiff("Payara Server 5", "UniTest", "Jersey", JERSEY_URL, Unchecked.function(this::unirestJSONGETRequest));

        timeDiff("Wildfly Server 14", "UniTest", "Resteasy", RESTEASY_URL, Unchecked.function(this::unirestJSONGETRequest));
    }

    @Test
    public void okhttpTesting() {
        System.out.println("## OKHttp");
        OkHttpClient client = new OkHttpClient();

        timeDiff("Jetty Server", "OKHttp", "Spark", SPARK_URL, Unchecked.function((String d) -> {
            return okhttpGETRequest(client, d);
        }).andThen(Unchecked.function(d -> d == null ? "" : d.string())));

        timeDiff("Jetty Server", "OKHttp", "Javalin", JAVALIN_URL, Unchecked.function((String u) -> {
            return okhttpGETRequest(client, u);
        }).andThen(Unchecked.function(d -> d == null ? "" : d.string())));

        timeDiff("Jetty Server", "OKHttp", "Spring Boot", SPRINGBOOT_URL, Unchecked.function((String u) -> {
            return okhttpGETRequest(client, u);
        }).andThen(Unchecked.function(d -> d == null ? "" : d.string())));

        timeDiff("Payara Server 5", "OKHttp", "Jersey", JERSEY_URL, Unchecked.function((String u) -> {
            return okhttpGETRequest(client, u);
        }).andThen(Unchecked.function(d -> d == null ? "" : d.string())));

        timeDiff("Wildfly Server 14", "OKHttp", "Resteasy", RESTEASY_URL, Unchecked.function((String u) -> {
            return okhttpGETRequest(client, u);
        }).andThen(Unchecked.function(d -> d == null ? "" : d.string())));

    }

    private ResponseBody okhttpGETRequest(OkHttpClient client, String url) throws IOException {
        Request req = new Request.Builder()
                .url(url)
                .build();

        Response res = client.newCall(req).execute();
        return res.body();
    }

    private String jerseyGetRequest(String u) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(u);
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
        int count;
//        for (int p = 100; p <= 1000; p *= 10) {
            count = 1000;
            JSONObject result = new JSONObject();
            result.put("start-time", System.nanoTime());
            long begin = System.nanoTime();
            long[] times = new long[count];
            for (int i = 0; i < count; i++) {
                long startTime = System.nanoTime();
                func.apply(url);
//            System.out.println(data);
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
            System.out.println(serverTitle + " " + count);
            System.out.println(diffAvg);
            data.put(result);
//        }
    }
}
