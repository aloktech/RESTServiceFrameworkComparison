/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.vp.micronaut;

import io.micronaut.runtime.Micronaut;

/**
 *
 * @author pintu
 */
public class Application {

    public static void main(String[] args) {
        Micronaut.run(HelloController.class);
    }
}
