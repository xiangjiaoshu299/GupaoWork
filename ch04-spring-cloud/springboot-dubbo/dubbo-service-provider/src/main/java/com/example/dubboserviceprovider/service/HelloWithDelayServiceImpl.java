package com.example.dubboserviceprovider.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.IHelloWithDelayService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@DubboService
public class HelloWithDelayServiceImpl implements IHelloWithDelayService {

    @Override
    public String sayHelloWithDelay(int count) {

        try {
            for (int i = 0; i < count; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("睡眠第" + i + "s");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello and sleep" + count + "s";
    }

    @Override
    public CompletableFuture<String> sayHelloAndGetFuture(int count) {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

            try {
                for (int i = 0; i < count; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("睡眠第" + i + "s");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello and sleep" + count + "s";

        });

        return future;
    }
}
