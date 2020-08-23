package com.example.orderserviceprovider;

import org.example.IOrderService;
import org.example.dto.Order;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

//虽然这个类叫service，但是它是Feign的service，是要发布服务的，所以用也是Controller
@RestController
public class OrderServiceImpl implements IOrderService {

    @Override
    public List<Order> orders() {
        Order order = new Order();
        order.setOrderId(1001);

        Order order2 = new Order();
        order2.setOrderId(1002);

        List<Order> list = Arrays.asList(order, order2);

        return list;
    }

    @Override
    public int insert(Order order) {

        return 1111;
    }
}
