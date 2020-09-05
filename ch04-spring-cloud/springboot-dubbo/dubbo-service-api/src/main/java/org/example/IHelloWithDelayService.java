package org.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface IHelloWithDelayService {

    String sayHelloWithDelay(int count);

    CompletableFuture<String> sayHelloAndGetFuture(int count);

}
