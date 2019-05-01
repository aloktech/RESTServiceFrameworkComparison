package com.imos.server;

import com.imos.core.JSONResult;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.validation.Validated;
import io.reactivex.Single;
import java.time.LocalTime;
import javax.validation.constraints.NotBlank;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author p
 */
@Controller("/")
@Validated
public class RESTController {

    @Get(uri = "/rest/micronaut", produces = MediaType.TEXT_PLAIN)
    public Single<String> hello(@NotBlank String name) {
        String result = new JSONResult.JSONResultBuilder()
                    .javaHome(System.getProperty("java.home"))
                    .data("Hello World")
                    .time(LocalTime.now().toString())
                    .restService("Micronaut")
                    .server("Yet to know")
                    .build()
                    .stringify();
        return Single.just(result);
    }
}
