package com.vividseats.minivivid.fulfillment;

import io.opentracing.Tracer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableRabbit
@SuppressWarnings("unused")
public class FulfillmentApplication {

    final static String SHIP_ITEM_TOPIC = "ShipItem";

    @Autowired
    Tracer tracer;

    public static void main(String[] args) {
        SpringApplication.run(FulfillmentApplication.class, args);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter());
        return template;
    }

    // New Order Queue

    @Bean
    public Queue newOrderQueue() {
        return new Queue("Fulfillment.NewOrders", false);
    }

    @Bean
    public TopicExchange newOrdersExchange() {
        return new TopicExchange("NewOrders", false, false);
    }

    @Bean
    public Binding inboundNewOrderExchangeBinding() {
        return BindingBuilder.bind(newOrderQueue()).to(newOrdersExchange()).with("");
    }

    // Payment Completed Queue

    @Bean
    public Queue newPaymentCompletedQueue() {
        return new Queue("Fulfillment.PaymentCompleted", false);
    }

    @Bean
    public TopicExchange newPaymentCompletedExchange() {
        return new TopicExchange("PaymentCompleted", false, false);
    }

    @Bean
    public Binding inboundNewPaymentCompletedExchangeBinding() {
        return BindingBuilder.bind(newPaymentCompletedQueue()).to(newPaymentCompletedExchange()).with("");
    }

    // ShipItem Queue

    @Bean
    public TopicExchange newShipItemExchange() {
        return new TopicExchange(SHIP_ITEM_TOPIC, false, false);
    }

}
