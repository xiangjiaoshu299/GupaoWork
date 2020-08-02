package com.example.hystriximitation;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.*;

@Slf4j
@Aspect
@Component
public class MyHystrixCommandAspect {

    @Pointcut("@annotation(MyHystrixCommand)")
    public void pointcut(){}

    ExecutorService executorService = Executors.newFixedThreadPool(4);

    //注: 这里加 @annotation(xxx)的原因，是因为需要获取到拦截方法的那个注解
    @Around("pointcut()&&@annotation(myHystrixCommand)")
    public Object doPointcut(ProceedingJoinPoint joinPoint, MyHystrixCommand myHystrixCommand){
        Future<Object> future = executorService.submit(() -> {
            Object proceed = null;
            try {
                proceed = joinPoint.proceed();//真正要执行的方法
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return proceed;
        });

        Object res = null;
        try {
            res = future.get(myHystrixCommand.timeout(), TimeUnit.MILLISECONDS);
            return res;

        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            //在指定的时间内，未获取到结果
            //e.printStackTrace();
            log.error("err:{}", e.toString());

            //执行熔断方法
            String fallbackMethod = myHystrixCommand.fallbackMethod();
            if(StrUtil.isNotEmpty(fallbackMethod)){

                return invokeFallback(joinPoint, fallbackMethod);

            }
        }

        return null;
    }

    public Object invokeFallback(ProceedingJoinPoint joinPoint, String fallbackMethodName){

        //获取被代理的方法的参数、Method
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?>[] parameterTypes = method.getParameterTypes();

        //获取代理方法
        try {
            Object target = joinPoint.getTarget();
            Method fallbackMethod = target.getClass().getMethod(fallbackMethodName, parameterTypes);

            return fallbackMethod.invoke(target, joinPoint.getArgs());

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}