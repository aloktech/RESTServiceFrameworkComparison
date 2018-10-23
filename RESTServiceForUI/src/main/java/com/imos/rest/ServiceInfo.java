/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest;

import com.owlike.genson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author pintu
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ServiceInfo implements Serializable {

    private static final long serialVersionUID = -4987670015611231250L;

    @JsonProperty("server")
    private String serverName;
    @JsonProperty("rest_service")
    private String restServiceName;
    @JsonProperty("url_get")
    private String urlGET;
}
