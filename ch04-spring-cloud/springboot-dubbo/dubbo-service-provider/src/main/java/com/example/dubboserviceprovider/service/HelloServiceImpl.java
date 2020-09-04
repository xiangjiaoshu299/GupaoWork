package com.example.dubboserviceprovider.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.IHelloService;

@DubboService
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello() {
        return "hello ghf";
    }
}
