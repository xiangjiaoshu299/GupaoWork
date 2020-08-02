package com.example.gatewaypersistence;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class MyRouteDefinitionRepository implements RouteDefinitionRepository {

    @Autowired
    private RouteDefinitionService routeDefinitionService;

    //项目启动或刷新时调用，刷新方法：POST http://localhost:8080/actuator/gateway/refresh
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {

        List<RouteDefinition> list = routeDefinitionService.getList();
        Flux<RouteDefinition> fluxList = Flux.fromIterable(list);
        log.info("获取list");
        return fluxList;
    }

    //参见：https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.2.3.RELEASE/reference/html/#creating-and-deleting-a-particular-route
    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(def -> {
            routeDefinitionService.add(def);

            log.info("添加{}", def.getId());
            return Mono.empty();
        });
    }

    //删除方式 eg: DELETE http://localhost:8080/actuator/gateway/routes/sec_route
    @Override
    public Mono<Void> delete(Mono<String> routeId) {

        return routeId.flatMap(def -> {
            routeDefinitionService.delete(def);
            log.info("删除{}", routeId);
            return Mono.empty();

        });
    }
}
