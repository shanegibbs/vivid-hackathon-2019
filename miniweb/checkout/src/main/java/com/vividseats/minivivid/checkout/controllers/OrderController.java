package com.vividseats.minivivid.checkout.controllers;

import com.vividseats.minivivid.common.checkout.Order;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("orders")
@SuppressWarnings("unused")
public class OrderController {

    private static Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    Tracer tracer;

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

        Span serverSpan = tracer.activeSpan();
        Span span = tracer.buildSpan("busPushNewOrders")
                .asChildOf(serverSpan.context())
                .start();

        try {
            order.setOrderId(randomUUIDString);
            rabbitTemplate.convertAndSend("NewOrders", "", order);
            LOG.info("NewOrder: " + order);
        } finally {
            span.finish();
        }

        return order;
    }

}
