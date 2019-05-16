package com.vividseats.minivivid.fulfillment;

import com.vividseats.minivivid.common.checkout.Order;
import com.vividseats.minivivid.common.checkout.PaymentCompleted;
import com.vividseats.minivivid.fulfillment.FulfillmentApplication;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

@Component
@SuppressWarnings("unused")
public class ReserveItemProcessor {

    @Autowired
    Tracer tracer;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static int retries = 3;

    @RabbitListener(queues = "Fulfillment.NewOrders")
    public void reserveItem(Order in) throws InterruptedException {
        System.out.println("Received " + in);

        Span serverSpan = tracer.activeSpan();
        Span span = tracer.buildSpan("reservingItem")
                .asChildOf(serverSpan.context())
                .start();

        try {
            Thread.sleep(100);

            if ("12345".equals(in.getUserId())) {
                retries -= 1;
                if (retries <= 0) {
                    retries = 3;
                    return;
                }
                throw new RuntimeException("Item already reserved");
            }

            retries = 3;
        } finally {
            span.finish();
        }

    }

}
