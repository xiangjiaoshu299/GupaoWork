package com.example.springbootnacosdemo;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//提示：需要在nacos中新建一项配置，dataId设置springboot-nacos-demo，设置info=infoaaa，其它选项默认即可，格式可以用txt
@NacosPropertySource(dataId = "springboot-nacos-demo", autoRefreshed = true)
@RestController
public class ConfController {

    //spring-cloud-nacos中的方式：
    //@Value("${value:not-find-conf-in-nacos}")
    //private String info;

    //springboot-nacos的方式
    @NacosValue(value = "${info:not-find-conf-in-nacos}", autoRefreshed = true)
    private String info;

    @GetMapping("conf")
    public String getConf() {

        return info;
    }
}
