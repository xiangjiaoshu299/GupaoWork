package com.example.hystriximitation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: ghf
 */
@Slf4j
@RestController
public class MyHystrixController {

    @MyHystrixCommand(fallbackMethod = "showTimeout", timeout = 3000)//timeout = 6000
    @GetMapping("getCountDesc/{count}")
    public String getCountDesc(@PathVariable int count){

        log.info("传入的count：{}", count);

        if(count %2 == 0){
            try {
                //模拟使用restTemplate时，请求超时
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return "数字：" + count;
    }

    public void showTimeout(int count){
        log.info("熔断超时了");
    }
}
