/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author alok.meher
 */
public class Deployment {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        String data = Files.readString(Paths.get(Deployment.class.getClassLoader().getResource("default.json").toURI()));
        InetAddress addr = Inet4Address.getLocalHost();
        data = data.replaceAll("\\$\\{hostname\\}", addr.getHostAddress());
//        System.out.println(data);
        HttpClient httpClient = HttpClient.newBuilder()
                .build();
        String deleteUrl = "http://${hostname}:8091/RESTServiceForUI/rest/deregisall";
        deleteUrl = deleteUrl.replaceAll("\\$\\{hostname\\}", addr.getHostAddress());
        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(URI.create(deleteUrl))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();
        HttpResponse<String> response = httpClient.send(deleteRequest, BodyHandlers.ofString());
        System.out.println(response.body());
        
        String registrationUrl = "http://${hostname}:8091/RESTServiceForUI/rest/regis";
        registrationUrl = registrationUrl.replaceAll("\\$\\{hostname\\}", addr.getHostAddress());
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(registrationUrl))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(data))
                .build();
        response = httpClient.send(postRequest, BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
