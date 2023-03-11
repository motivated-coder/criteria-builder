package com.skd.controller;

import com.skd.dto.OrderDTO;
import com.skd.entity.Order;
import com.skd.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.skd.repository.OrderRepository;
import com.skd.service.OrderService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final OrderRepository orderRepository;

    @PostConstruct
    public void initialize(){
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setItem_state("NEW");
        order.setOrder_status("NEW");
        order.setOrderItems(List.of(orderItem));

        orderRepository.save(order);
    }

    @GetMapping("/get/{orderId}")
    public ResponseEntity<OrderDTO> getOrderDetails(@PathVariable("orderId") Integer orderId,
                                                 @RequestParam(name = "requiredOnly", required = false) String requiredOnly){
        String[] stringArray = requiredOnly.split(",");
        List<String> listString = new ArrayList<>(Arrays.asList(stringArray));
        OrderDTO orderDTO = orderService.getOrder(orderId,listString);
        return null;
    }
}
