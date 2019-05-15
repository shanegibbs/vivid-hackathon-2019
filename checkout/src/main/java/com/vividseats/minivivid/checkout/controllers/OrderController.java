package com.vividseats.minivivid.checkout.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {

    @GetMapping(value = "/{id}", produces = "application/json")
    public String getOrder(@PathVariable int id) {
        return "home";
    }

}
