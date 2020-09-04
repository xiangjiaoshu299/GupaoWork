package com.example.dubboserviceconsumer;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.IHelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @DubboReference
    private IHelloService helloService;

    @GetMapping("say")
    public String say(){
        return helloService.sayHello();
    }

}
