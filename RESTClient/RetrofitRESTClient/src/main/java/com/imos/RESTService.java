package com.imos;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 *
 * @author p
 */
public interface RESTService {

    @GET("/rest/{rest-service-type}")
    Call<ResponseBody> getData(@Path("rest-service-type") String user);
}
