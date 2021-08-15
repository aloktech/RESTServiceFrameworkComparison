/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ServiceLoader;

/**
 * @author pintu
 */
public enum HttpClientService {

    INSTANCE;

    private static final Logger LOG = LogManager.getLogger(HttpClientService.class);

    @Getter
    private ServiceLoader<HttpClientServiceProvider> loader;

    @Getter
    private int size;

    public void loadServices(ClassLoader classLoader) {
        try {
            LOG.info("start");
            loader = ServiceLoader.load(HttpClientServiceProvider.class, classLoader);
            LOG.info("loaded");
            int counter = 0;
            for (HttpClientServiceProvider httpClientServiceProvider : loader) {
                counter++;
            }
            size = counter;
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
