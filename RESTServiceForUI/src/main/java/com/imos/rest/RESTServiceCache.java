/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest;

import com.imos.ClientService;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.inject.Singleton;
import lombok.Getter;

/**
 *
 * @author pintu
 */
@Singleton
public class RESTServiceCache {

    @Getter
    private final Set<ServiceInfo> SERVICES = new HashSet<>();

    @Getter
    private final ClientService service = ClientService.INSTANCE;

    @PostConstruct
    public void config() {
        service.loadServices(RESTServiceCache.class.getClassLoader());
    }

    public void addService(ServiceInfo info) {
        SERVICES.add(info);
    }

    public void removeService(ServiceInfo info) {
        SERVICES.remove(info);
    }

    public Set<ServiceInfo> getAllActiveServices() {
        return SERVICES;
    }

    @Schedule(second = "*", minute = "1")
    public void checkRESTstatus() {
        System.out.println(LocalTime.now());
    }
}
