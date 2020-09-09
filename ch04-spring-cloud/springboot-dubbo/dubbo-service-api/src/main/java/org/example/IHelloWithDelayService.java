package org.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface IHelloWithDelayService {

    String sayHelloWithDelay(int count);

    //为了测试事件，拷贝的一个方法
    String sayHelloWithDelayCopy(int count);

    CompletableFuture<String> sayHelloAndGetFuture(int count);

}
