/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author pintu
 */
@ApplicationPath("/")
public class RESTConfig extends ResourceConfig {

    public RESTConfig() {
        packages("com.imos.rest");
    }
}
