/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos;

import io.sinistral.proteus.ProteusApplication;

/**
 *
 * @author pintu
 */
public class ProteusApp extends ProteusApplication {

    public static void main(String[] args) {

        ProteusApp app = new ProteusApp();
        app.addService(io.sinistral.proteus.services.SwaggerService.class);
        app.addController(RESTController.class);
        app.start();

    }
}
