/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 *
 * @author pintu
 */
public class OKHttpClient implements ClientServiceProvider {

    private OkHttpClient client = new OkHttpClient();

    @Override
    public void config() {
        client = new OkHttpClient();
    }

    @Override
    public String execute(String url) throws Exception {
        Request req = new Request.Builder()
                .url(url)
                .build();

        Response res;
        ResponseBody body = null;
        String data = "{}";
        try {
            res = client.newCall(req).execute();
            body = res.body();
            data = body == null ? data : body.string();
        } catch (Exception ex) {
            throw ex;
        }
        return (body == null || data == null) ? "{}" : data;
    }

    @Override
    public String getClientServiceName() {
        return "OKHttp Client";
    }

    @Override
    public void close() {
    }

}
