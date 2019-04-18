/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest.old;

import com.imos.ClientService;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author pintu
 */
@Log4j2
@ApplicationScoped
public class RESTServiceCache implements Serializable {

    @Getter
    private final Set<ServiceInfo> SERVICES = new HashSet<>();

    @Getter
    private final ClientService service = ClientService.INSTANCE;

    @PostConstruct
    public void config() {
        service.loadServices(RESTServiceCache.class.getClassLoader());
        log.info("Services configured");
    }

    public void addService(ServiceInfo info) {
        SERVICES.add(info);
        log.info("Service {} added: {}", info.getServerName(), info.toString());
        System.out.println(info);
    }

    public void removeService(ServiceInfo info) {
        log.info("Service {} remove: {}", info.getServerName(), info.toString());
        SERVICES.remove(info);
    }

    public Set<ServiceInfo> getAllActiveServices() {
        log.info("get all services");
        return SERVICES;
    }
}
