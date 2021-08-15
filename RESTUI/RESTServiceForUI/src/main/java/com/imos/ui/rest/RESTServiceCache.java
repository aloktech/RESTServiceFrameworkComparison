/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.ui.rest;

import com.imos.HttpClientService;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author pintu
 */
public enum RESTServiceCache implements Serializable {

    INSTANCE;

    private final Logger LOG = LogManager.getLogger(RESTServiceCache.class);

    @Getter
    private final Set<ServiceInfo> SERVICES = new HashSet<>();

    @Getter
    private final HttpClientService service = HttpClientService.INSTANCE;

    public void loadServices() {
        try {
            LOG.info("begin to service configure");
            service.loadServices(RESTServiceCache.class.getClassLoader());
            LOG.info("Services configured");
            LOG.info("Services availble: {}", HttpClientService.INSTANCE.getSize());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addService(ServiceInfo info) {
        SERVICES.add(info);
        LOG.info("Service {} added: {}", info.getServerName(), info.toString());
        System.out.println(info);
    }

    public void removeService(ServiceInfo info) {
        LOG.info("Service {} remove: {}", info.getServerName(), info.toString());
        SERVICES.remove(info);
    }

    public Set<ServiceInfo> getAllActiveServices() {
        LOG.info("get all services, {}", SERVICES.size());
        return SERVICES;
    }
}
