package com.example.dubboserviceconsumer;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.rpc.RpcContext;
import org.example.IHelloWithDelayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class HelloAsyncController {


    //异步配置法1：整个service都配置
    @DubboReference(timeout = 10 * 1000, async = true)

    //异步配置法2:方法级别配置
    //@DubboReference(timeout = 10 * 1000, methods = {@Method(name = "sayHelloWithDelay", async = true)})
    private IHelloWithDelayService helloWithDelayService;

    //异步调用，不关心结果
    @GetMapping("sayWithDelayAsync")
    public String sayWithDelay() {

        //因为是异步调用，所以会返回null
        String remoteCallRes = helloWithDelayService.sayHelloWithDelay(3);
        return "返回的结果：" + remoteCallRes;
    }

    //异步调用，取结果，法一：通过RpcContext对象去做。通过RpcContext去拿future，阻塞至拿到结果
    @GetMapping("sayByRpccontext")
    public String sayByRpccontext() {

        helloWithDelayService.sayHelloWithDelay(3);

        //future从RpcContext中拿
        Future<Object> future = RpcContext.getContext().getFuture();

        Object resAfterBlock = null;
        try {
            resAfterBlock = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "返回的结果：" + resAfterBlock;
    }

    //异步调用，取结果，法二：接口返回值设置成CompleteFuture。在@DubboReference()中，不配置这个方法异步，好像都可以，因为它都返回future了
    @GetMapping("sayByReturnFuture")
    public String sayByReturnFuture() {

        //future直接拿到
        CompletableFuture<String> future = helloWithDelayService.sayHelloAndGetFuture(3);
        String resAfterBlock = null;
        try {
            resAfterBlock = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "返回的结果：" + resAfterBlock;
    }

    //异步调用，取结果，法三：事件通知
    @GetMapping("sayWithEvent")
    public String sayWithEvent(){
        
    }


    //异步调用，取结果，法xxx：通过Rpccontext得到ResponseFuture对象。这里用到的ResponseFuture废弃了，百度没找到替换方案，懒得测试了
    //异步调用，阻塞至拿到结果，添加异常回调（不知道这个异常打印出来有啥子用，直接try也有异常啊）
/*    @GetMapping("sayWithDelayAsyncTry")
    public String sayWithDelayAsyncTry() {

        helloWithDelayService.sayHelloWithDelay(3);
        ResponseFuture future = (ResponseFuture) RpcContext.getContext().getFuture();
        future.setCallback(new ResponseCallback() {
            @Override
            public void done(Object response) {
                System.out.println("异步请求执行完成");
            }

            @Override
            public void caught(Throwable exception) {

                System.out.println("异步请求执行异常");
                exception.printStackTrace();
            }
        });
        Object resAfterBlock = null;
        try {
            resAfterBlock = future.get();
        } catch (RemotingException e) {
            e.printStackTrace();
        }

        return "返回的结果：" + resAfterBlock;
    }*/


}
