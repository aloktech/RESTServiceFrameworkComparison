/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest.old;

import com.alibaba.fastjson.annotation.JSONField;
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

    @JSONField(name = "server")
    private String serverName;

    @JSONField(name = "rest_service")
    private String restServiceName;

    @JSONField(name = "base_url")
    private String baseURL;
}
