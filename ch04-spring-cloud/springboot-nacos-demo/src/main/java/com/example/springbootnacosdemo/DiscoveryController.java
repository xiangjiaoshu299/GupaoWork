package com.example.springbootnacosdemo;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//springboot中，nacos做注册中心测试
@RestController
public class DiscoveryController {

    //提示，这个注解：Only support to inject types[[interface com.alibaba.nacos.api.config.ConfigService, interface com.alibaba.nacos.api.naming.NamingService, interface com.alibaba.nacos.api.naming.NamingMaintainService]]
    @NacosInjected
    private NamingService namingService;

    //得到注册中心上的所有服务列表
    //测试，访问 http://localhost:8080/instances?serviceName=springcloud-nacos-provider
    @GetMapping("instances")
    public List<Instance> instancesInNacos(String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }

    //注册一个服务到nacos上面
    @PostMapping("registry")
    public String registry(){
        try {
            namingService.registerInstance("provider2", "192.168.25.111", 8081,"clusterAAA");
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
