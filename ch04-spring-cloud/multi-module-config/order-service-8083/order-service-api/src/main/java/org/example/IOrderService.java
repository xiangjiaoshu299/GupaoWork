package org.example;


import org.example.dto.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("order-service")
public interface IOrderService {

    //openFeign和feign的区别之一在于，openFeign有丰富的rest风格注解，而feign只能用@RequestMapping
    @GetMapping("orders")
    List<Order> orders();

    @PostMapping("order")
    int insert(Order order);
}
