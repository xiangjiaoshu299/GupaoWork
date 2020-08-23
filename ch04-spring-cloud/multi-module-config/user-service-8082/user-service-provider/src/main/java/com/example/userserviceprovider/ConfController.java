package com.example.userserviceprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope//允许刷新配置
@RestController
public class ConfController {

    @Value("${conf-desc}")
    private String desc;

    //user-service.properties中的配置
    @Value("${app-func}")
    private String appFunc;

    @GetMapping("getDesc")
    public String getDesc(){

        return "appFunction: " + appFunc + ", conf desc: " + desc;
    }
}
