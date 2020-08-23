package com.example.userserviceprovider;

import org.example.IOrderService;
import org.example.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FeignController {

    @Resource
    private IOrderService iOrderService;

    @GetMapping("orders")
    public List<Order> orders(){
        return iOrderService.orders();
    }

    @PostMapping("order")
    public int order(Order order){

        return iOrderService.insert(order);
    }

}
