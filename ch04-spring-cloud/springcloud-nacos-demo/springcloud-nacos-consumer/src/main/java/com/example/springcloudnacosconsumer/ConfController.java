package com.example.springcloudnacosconsumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试获取配置中心的配置
 */
@RefreshScope//允许刷新配置，就是nacos提交配置的时候，这里也更新配置
@RestController
public class ConfController {

    //注意：请在nacos中先添加这个info的配置

    //冒号后面的是默认值
    @Value("${info:no-info-conf-find-nacos}")
    private String info;


    @GetMapping("conf")
    public String getConf(){

        return info;
    }

}
