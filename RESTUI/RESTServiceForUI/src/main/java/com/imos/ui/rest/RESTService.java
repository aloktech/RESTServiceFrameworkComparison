/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.ui.rest;

import com.alibaba.fastjson.JSON;
import com.imos.HttpClientServiceProvider;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.lambda.Unchecked;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author pintu
 */
@Log4j2
public class RESTService implements Serializable {

    private static final Logger LOG = LogManager.getLogger(RESTService.class);
    private final RESTServiceCache cache = RESTServiceCache.INSTANCE;
    private JSONArray data = new JSONArray();

    public RESTService() {
        LOG.info("Services to load");
        cache.loadServices();
        LOG.info("Services loaded");
    }

    public JSONArray getAllServices() throws JSONException {
        JSONArray array = new JSONArray();
        cache.getAllActiveServices()
                .forEach(s -> generateService(s, array));
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
        array.forEach(s -> addService(s));
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
        array.forEach(s -> cache.removeService(JSON.parseObject(s.toString(), ServiceInfo.class)));
        json = new JSONObject(data1);
        json.put("msg", array.length() + " services are removed");
        return json;
    }

    public String getDataByIteration(int iteration) {

        data = new JSONArray();
        cache.getAllActiveServices()
                .forEach(s -> execute(iteration, s));
        JSONObject json = new JSONObject();
        json.put("time", LocalTime.now().toString());
        json.put("result", data.toString());

        return json.toString(4);
    }

    public JSONObject getAllData() {

        data = new JSONArray();
        log.info("Request to collect all data");
        cache.getAllActiveServices()
                .forEach(s -> execute(1000, s));

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
                .forEach(s -> executeGetRequest(info, s, iteration));
    }

    private void executeGetRequest(ServiceInfo info, HttpClientServiceProvider service, int iteration) {
        String url = info.getBaseURL();
        try {
            service.config();
            String strData = service.execute(url);
            checkDataValidity(strData);
            timeDiff(data, info.getServerName(), service.getClientServiceName(), info.getRestServiceName(),
                    url, Unchecked.function(u -> service.execute(u)), iteration);
        } catch (Exception e) {
            log.error("On Service {}, By Client {}, Failed for url : '{}' caused by {}", info.getRestServiceName(), service.getClientServiceName(), url, e.getMessage());
        } finally {
            service.close();
        }
    }

    private void checkDataValidity(String data) {
        try {
            log.info(data);
            JSONObject json = new JSONObject(data);
            if (json.keySet().isEmpty()) throw new IllegalStateException("Empty JSON data: ");
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
            double diffAvg = IntStream.range(0, count)
                    .parallel()
                    .mapToObj(index -> {
                        long startTime = System.nanoTime();
                        func.apply(url);
                        return (System.nanoTime() - startTime);
                    })
                    .collect(Collectors.averagingLong(Long::longValue));
            long end = System.nanoTime();
            result.put("end-time", System.nanoTime());
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
            Objects.requireNonNull(data).put(error);
        }
        return data;
    }
}
