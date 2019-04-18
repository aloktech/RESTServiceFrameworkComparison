/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.ui.rest;

import com.alibaba.fastjson.JSON;
import com.imos.ClientServiceProvider;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.lambda.Unchecked;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author pintu
 */
@Log4j2
public class RESTService implements Serializable {

    private static final Logger LOG = LogManager.getLogger(RESTService.class);

    private JSONArray data = new JSONArray();

    private final RESTServiceCache cache = RESTServiceCache.INSTANCE;

    public RESTService() {
        LOG.info("Services to load");
        cache.loadServices();
        LOG.info("Services loaded");
    }

    public JSONArray getAllServices() throws JSONException {
        JSONArray array = new JSONArray();
        cache.getAllActiveServices()
                .forEach(s -> {
                    generateService(s, array);
                });
        log.info("Request for AllServices");
        return array;
    }

    private void generateService(ServiceInfo s, JSONArray array) throws JSONException {
        JSONObject service = new JSONObject();
        service.put("server", s.getServerName());
        service.put("rest-service", s.getRestServiceName());
        array.put(service);
    }

    public JSONObject registration(String data) throws JSONException {
        JSONObject json = new JSONObject(data);
        JSONArray array = json.getJSONArray("services");
        array.forEach(s -> {
            addService(s);
        });
        json = new JSONObject(data);
        json.put("msg", array.length() + " services are added");
        return json;
    }

    private void addService(Object s) {
        cache.addService(JSON.parseObject(s.toString(), ServiceInfo.class));
        log.info("Registration of {}", s);
    }

    public void deregistrationOfAll() throws JSONException {
        cache.getAllActiveServices().clear();
    }

    public JSONObject deregistration(String data1) throws JSONException {
        JSONObject json = new JSONObject(data1);
        JSONArray array = json.getJSONArray("services");
        array.forEach(s -> {
            cache.removeService(JSON.parseObject(s.toString(), ServiceInfo.class));
        });
        json = new JSONObject(data1);
        json.put("msg", array.length() + " services are removed");
        return json;
    }

    public String getDataByIteration(int iteration) {

        data = new JSONArray();
        cache.getAllActiveServices()
                .stream()
                .forEach(s -> {
                    execute(iteration, s);
                });
        JSONObject json = new JSONObject();
        json.put("time", LocalTime.now().toString());
        json.put("result", data.toString());

        return json.toString(4);
    }

    public JSONObject getAllData() {

        data = new JSONArray();
        log.info("Request to collect all data");
        cache.getAllActiveServices()
                .stream()
                .forEach(s -> {
                    execute(1000, s);
                });

        JSONObject json = new JSONObject();
        json.put("time", LocalTime.now().toString());
        json.put("result", data);
        log.info("All data collected of count {}", data.length());
        return json;
    }

    private void execute(int iteration, ServiceInfo info) {
        log.info("execute for service {}", info.getRestServiceName());
        cache.getService()
                .getLoader()
                .forEach(s -> {
                    executeGetRequest(info, s, iteration);
                });
    }

    private void executeGetRequest(ServiceInfo info, ClientServiceProvider s, int iteration) {
        String url = info.getBaseURL();
        try {
            s.config();
            String d = s.execute(url);
            checkDataValidity(d);
            data = timeDiff(data, info.getServerName(), s.getClientServiceName(), info.getRestServiceName(),
                    url, Unchecked.function(u -> s.execute(u)), iteration);
        } catch (Exception e) {
            log.error("On Service {}, Failed for url : '{}' caused by {}", info.getRestServiceName(), url, e.getMessage());
        } finally {
            s.close();
        }
    }

    private void checkDataValidity(String data) {
        try {
            log.info(data);
            JSONObject json = new JSONObject(data);
            if (json.keySet().isEmpty()) {
                throw new IllegalStateException("Empty JSON data");
            }
        } catch (JSONException e) {
            throw new IllegalStateException("Invalid data : " + e.getMessage());
        }
    }

    private <I, O> JSONArray timeDiff(JSONArray data, String serverName, String clientServiceName, String serverServiceName, I url, Function<I, O> func, int iteration) {
        JSONObject result = new JSONObject();
        int count;
        try {
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
            result.put("server-rest-service-provider", serverServiceName);
            result.put("client-rest-service-provider", clientServiceName);
            result.put("server", serverName);
            data.put(result);
        } catch (Exception e) {
            Throwable th = e;
            JSONArray errorArray = new JSONArray();
            while (Objects.nonNull(th)) {
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
        return data;
    }
}
