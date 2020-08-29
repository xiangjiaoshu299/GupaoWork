package com.example.springbootnacosdemo;

import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringbootNacosDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNacosDemoApplication.class, args);
    }

}
