/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos;

import java.util.ServiceLoader;
import lombok.Getter;

/**
 *
 * @author pintu
 */
public enum ClientService {

    INSTANCE;

    @Getter
    private ServiceLoader<ClientServiceProvider> loader;

    public void loadServices(ClassLoader classLoader) {
        loader = ServiceLoader.load(ClientServiceProvider.class, classLoader);
    }
}
