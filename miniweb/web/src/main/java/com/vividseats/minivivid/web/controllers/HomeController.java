package com.vividseats.minivivid.web.controllers;

import com.vividseats.minivivid.common.checkout.Order;
import com.vividseats.minivivid.web.OrderForm;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private static Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    RestTemplate restTemplate;

    @Value("${minivivid.checkout.url}")
    String checkoutUrl;

    @Autowired
    Tracer tracer;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", "Minivivid Web bla");
        return "home";
    }

    @PostMapping("/")
    public String orderItem(Model model, @ModelAttribute OrderForm orderForm) {
        Order order = orderForm.toOrder();
        MDC.put("OrderId", order.getOrderId());
        LOG.info("Received order request: " + order);

        Span serverSpan = tracer.activeSpan();
        Span span = tracer.buildSpan("submitOrderRequest")
                .asChildOf(serverSpan.context())
                .start();

        try {
            ResponseEntity<Order> createdOrder = restTemplate.postForEntity(checkoutUrl + "/orders", order, Order.class);
            LOG.info("Created order: " + createdOrder.getBody());
        } finally {
            span.finish();
        }

        return "home";
    }

}
