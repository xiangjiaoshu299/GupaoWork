package com.example.hystrixreqcombine.method2;

import com.example.hystrixreqcombine.comm.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class UserController2 {

    @Autowired
    MyHystrixMergeService myHystrixMergeService;


    /**
     * 模拟合并请求测试(非注解)
     * 这里通过
     */
    @GetMapping("/user/{id}")
    public void collapseTest(@PathVariable int id){
        Future<String> future = myHystrixMergeService.merge(3);
        System.out.println("请求id为" + 3 + "的用户");
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        };
    }
}
