package com.example.dubboserviceconsumer;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.IHelloService;
import org.example.IHelloWithDelayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @DubboReference
    private IHelloService helloService;

    @DubboReference(timeout = 10*1000)
    private IHelloWithDelayService helloWithDelayService;

    @GetMapping("say")
    public String say(){
        return helloService.sayHello();
    }

    @GetMapping("sayWithDelay")
    public String sayWithDelay(){
        return helloWithDelayService.sayHelloWithDelay(3);
    }
}
