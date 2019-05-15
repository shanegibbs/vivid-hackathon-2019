package com.vividseats.minivivid.payment;

import com.vividseats.minivivid.common.checkout.Order;
import io.opentracing.Tracer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
public class PaymentApplication {

    final static String PAYMENT_COMPLETED_TOPIC = "PaymentCompleted";

    @Autowired
    Tracer tracer;

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
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

    @Bean
    public Queue newOrderQueue() {
        return new Queue("Payments.NewOrders", false);
    }

    @Bean
    public TopicExchange newOrdersExchange() {
        return new TopicExchange("NewOrders", false, false);
    }

    @Bean
    public Binding inboundNewOrderExchangeBinding() {
        return BindingBuilder.bind(newOrderQueue()).to(newOrdersExchange()).with("");
    }

    @Bean
    public TopicExchange newPaymentCompleteExchange() {
        return new TopicExchange(PAYMENT_COMPLETED_TOPIC, false, false);
    }

}
