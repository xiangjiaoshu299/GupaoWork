package com.example.hystrixreqcombine.method2;

import com.example.hystrixreqcombine.comm.UserInfo;
import com.example.hystrixreqcombine.comm.UserServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class MyHystrixMergeService {

    @Autowired
    private UserServiceImpl userService;

    //指定批处理的方法，设置合并200ms之内的请求
    @HystrixCollapser(batchMethod = "getMerge", collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "200")})
    public Future<String> merge(Integer id) {
        //不会进入这个方法体
        return null;
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public List<String> getMerge(List<Integer> ids) {
        System.out.println("合并的请求：" + ids.toString());

        List<UserInfo> users = userService.findAll(ids);
        List<String> userNames = users.stream().map(u -> u.getName()).collect(Collectors.toList());

        System.out.println("合并后的结果" + userNames);
        return userNames;
    }


    /**
     * 降级方法的参数，返回值类型，返回值数量要和上面的方法对应
     *
     * @param ids
     * @return
     */
    public List<String> fallback(List<Integer> ids) {
        List<String> list = new ArrayList<>();
        list.add("请求合并失败");

        return list;
    }

}