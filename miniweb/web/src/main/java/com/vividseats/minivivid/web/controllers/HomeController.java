package com.vividseats.minivivid.web.controllers;

import com.vividseats.minivivid.common.checkout.Order;
import com.vividseats.minivivid.web.OrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@SuppressWarnings("unused")
public class HomeController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", "Minivivid Web bla");
        return "home";
    }

    @PostMapping("/")
    public String orderItem(Model model, @ModelAttribute OrderForm orderForm) {
        Order order = orderForm.toOrder();
        System.out.println("Received request: " + order);

        ResponseEntity<Order> createdOrder = restTemplate.postForEntity("http://localhost:8081/orders", order, Order.class);
        System.out.println("Created: " + createdOrder);

        return "home";
    }

}
