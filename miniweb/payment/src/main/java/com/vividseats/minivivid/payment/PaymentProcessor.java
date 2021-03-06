package com.vividseats.minivivid.payment;

import com.vividseats.minivivid.common.checkout.Order;
import com.vividseats.minivivid.common.checkout.PaymentCompleted;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@SuppressWarnings("unused")
public class PaymentProcessor {

    @Autowired
    Tracer tracer;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "Payments.NewOrders")
    public void processOrderPayments(Order in) throws InterruptedException {
        System.out.println("Received " + in);

        Span serverSpan = tracer.activeSpan();
        Span span = tracer.buildSpan("chargingCreditCard")
                .asChildOf(serverSpan.context())
                .start();

        try {
            Thread.sleep(500);
        } finally {
            span.finish();
        }

        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();

        PaymentCompleted paymentCompeted = new PaymentCompleted(randomUUIDString, in.getOrderId());
        System.out.println("Sending " + paymentCompeted);

        rabbitTemplate.convertAndSend(PaymentApplication.PAYMENT_COMPLETED_TOPIC, "", paymentCompeted);
    }

}
