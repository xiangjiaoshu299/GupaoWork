package com.example.hystrixreqcombine.method2;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyCommandLineRunner implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        System.out.println("初始化hystrix");
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
    }
}
