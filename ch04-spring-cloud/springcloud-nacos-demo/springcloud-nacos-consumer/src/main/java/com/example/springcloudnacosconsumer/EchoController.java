package com.example.springcloudnacosconsumer;

import org.apache.dubbo.config.annotation.Reference;
import org.example.EchoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

    @Reference
    private EchoService echoService;

    @GetMapping("echo")
    public String echo(){
        return echoService.echo("ghf");
    }
}
