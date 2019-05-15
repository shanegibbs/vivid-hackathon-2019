package com.vividseats.minivivid.checkout.controllers;

import com.vividseats.minivivid.common.checkout.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("orders")
@SuppressWarnings("unused")
public class OrderController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/{id}", produces = "application/json")
    public Order getOrder(@PathVariable int id) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();

        return new Order(randomUUIDString, randomUUIDString, "pending", 30000, "Foo");
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();

        order.setOrderId(randomUUIDString);
        rabbitTemplate.convertAndSend("NewOrders", "", order);
        return order;
    }

}
