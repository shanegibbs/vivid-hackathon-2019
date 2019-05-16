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

@Component
@SuppressWarnings("unused")
public class ReserveItemProcessor {

    @Autowired
    Tracer tracer;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "Fulfillment.NewOrders")
    public void reserveItem(Order in) throws InterruptedException {
        System.out.println("Received " + in);

        Span serverSpan = tracer.activeSpan();
        Span span = tracer.buildSpan("reservingItem")
                .asChildOf(serverSpan.context())
                .start();

        try {
            Thread.sleep(100);
        } finally {
            span.finish();
        }

    }

}
