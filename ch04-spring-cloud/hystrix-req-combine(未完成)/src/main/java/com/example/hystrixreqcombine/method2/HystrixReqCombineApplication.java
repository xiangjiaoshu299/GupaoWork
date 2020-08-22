package com.example.hystrixreqcombine.method2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Component;

@ComponentScan(basePackages = {"com.example.hystrixreqcombine.comm", "com.example.hystrixreqcombine.method2"})
@EnableCircuitBreaker
@SpringBootApplication
public class HystrixReqCombineApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixReqCombineApplication.class, args);
    }

}
