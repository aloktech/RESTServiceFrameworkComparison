package com.imos;

import com.google.auto.service.AutoService;
import lombok.extern.log4j.Log4j2;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * @author p
 */
@Log4j2
@AutoService(HttpClientServiceProvider.class)
public class RetrofitHttpClient implements HttpClientServiceProvider {

    Retrofit.Builder retrofitBuilder;
    Retrofit retrofit;
    RESTService service;
    String serviceType;

    @Override
    public void config() {
        retrofitBuilder = new Retrofit.Builder();
    }

    @Override
    public String execute(String url) {
        String data = "{}";
        try {
            if (retrofit == null) {
                if (url.endsWith("/")) {
                    url = url.substring(0, url.lastIndexOf("/"));
                }
                serviceType = url.substring(url.lastIndexOf("/") + 1);
                url = url.substring(0, url.lastIndexOf("/"));
                url = url.substring(0, url.lastIndexOf("/"));
                retrofit = retrofitBuilder
                        .baseUrl(url + "/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service = retrofit.create(RESTService.class);
            }
            Call<ResponseBody> call = service.getData(serviceType);
            Response<ResponseBody> response = call.execute();
            ResponseBody body = response.body();
            if (body != null) {
                data = body.string();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return data;
    }

    @Override
    public String getClientServiceName() {
        return "Retrofit Client";
    }

    @Override
    public void close() {
    }
}
