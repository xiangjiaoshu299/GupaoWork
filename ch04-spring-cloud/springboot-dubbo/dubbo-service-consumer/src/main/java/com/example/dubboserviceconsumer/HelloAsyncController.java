package com.example.dubboserviceconsumer;

import com.alibaba.dubbo.remoting.exchange.ResponseCallback;
import com.alibaba.dubbo.remoting.exchange.ResponseFuture;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.rpc.RpcContext;
import org.example.IHelloWithDelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class HelloAsyncController {


    @Autowired
    private MyNoticeService myNoticeService;

    //异步配置法1：整个service都配置
    @DubboReference(timeout = 10 * 1000, async = true)
    //异步配置法2:方法级别配置
    //@DubboReference(timeout = 10 * 1000, methods = {@Method(name = "sayHelloWithDelay", async = true)})
    ////事件配置（springboot版本尝试失败）
    //@DubboReference(timeout = 10 * 1000, async = true, methods = {
    //        @Method(name = "sayHelloWithDelayCopy",
    //                async = true,
    //                //oninvoke = "myNoticeService.onInvoke"
    //                onreturn = "myNoticeService.onreturn"
    //        )
    //})
    private IHelloWithDelayService helloWithDelayService;


    //异步调用，不关心结果
    @GetMapping("sayWithDelayAsync")
    public String sayWithDelay() {

        //因为是异步调用，所以会返回null
        String remoteCallRes = helloWithDelayService.sayHelloWithDelay(3);
        return "返回的结果：" + remoteCallRes;
    }

    //异步调用，取结果，法一：通过RpcContext对象去做。通过RpcContext去拿future，阻塞至拿到结果
    @GetMapping("sayByGetFuture")
    public String sayByRpccontext() {

        helloWithDelayService.sayHelloWithDelay(3);

        //future从RpcContext中拿
        //Future<Object> future = RpcContext.getContext().getFuture();//这个也行
        Future<Object> future = RpcContext.getContext().getCompletableFuture();

        Object resAfterBlock = null;
        try {
            resAfterBlock = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "返回的结果：" + resAfterBlock;
    }

    //异步调用，取结果，法二：通过Rpccontext得到 CompletableFuture 对象，后台异步得到结果。
    @GetMapping("sayByGetCompletableFuture")
    public String sayWithDelayAsyncTry() {

        helloWithDelayService.sayHelloWithDelay(3);
        CompletableFuture<Object> completableFuture = RpcContext.getContext().getCompletableFuture();

        final Object[] resAfterBlock = new Object[1];

        //这个方法不阻塞，所以拿不到结果
        completableFuture.whenComplete((res, exp)->{
            if(exp != null){
                exp.printStackTrace();
            }else{
                resAfterBlock[0] = res;
                System.out.println("结果：" + res);//过几s后，会打印结果
            }
        });

        return "服务端正在后台等待结果";
    }

    //异步调用，取结果，法三：接口返回值设置成CompleteFuture。在@DubboReference()中，不配置这个方法异步，好像都可以，因为它都返回future了
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

    ////异步调用，取结果，法x：事件通知（尝试失败，网上都是xml的配置，我用springboot尝试失败了，运行时找不到事件回调的方法）
    //@GetMapping("sayWithEvent")
    //public String sayWithEvent(){
    //    helloWithDelayService.sayHelloWithDelayCopy(3);
    //    return "调用结束";
    //}
}
