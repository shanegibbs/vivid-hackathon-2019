package com.vividseats.minivivid.web;

import io.opentracing.Tracer;
import io.opentracing.contrib.spring.web.client.TracingRestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args){
        SpringApplication.run(WebApplication.class, args);
    }

    @Autowired
    Tracer tracer;

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new TracingRestTemplateInterceptor(tracer)));
        return restTemplate;
    }

}
