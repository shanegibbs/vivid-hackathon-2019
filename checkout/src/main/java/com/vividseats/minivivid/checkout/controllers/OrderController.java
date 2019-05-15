package com.vividseats.minivivid.checkout.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vividseats.minivivid.common.checkout.Order;

@RestController
@RequestMapping("orders")
public class OrderController {

    @GetMapping(value = "/{id}", produces = "application/json")
    public Order getOrder(@PathVariable int id) {
        return new Order(1, 2, "pending", 30000, "Foo");
    }

}
