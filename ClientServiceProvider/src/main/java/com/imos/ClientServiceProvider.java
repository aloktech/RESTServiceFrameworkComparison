/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos;

/**
 *
 * @author pintu
 */
public interface ClientServiceProvider {
    
    void config();
    
    void close();
    
    String execute(String url) throws Exception;
    
    String getClientServiceName();
}
