/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos;

import java.util.Iterator;
import java.util.ServiceLoader;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author pintu
 */
public enum ClientService {

    INSTANCE;

    private static final Logger LOG = LogManager.getLogger(ClientService.class);

    @Getter
    private ServiceLoader<ClientServiceProvider> loader;

    @Getter
    private int size;

    public void loadServices(ClassLoader classLoader) {
        try {
            LOG.info("start");
            loader = ServiceLoader.load(ClientServiceProvider.class, classLoader);
            LOG.info("loaded");
            int counter = 0;
            Iterator<ClientServiceProvider> itr = loader.iterator();
            while (itr.hasNext()) {
                counter++;
                itr.next();
            }
            size = counter;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
