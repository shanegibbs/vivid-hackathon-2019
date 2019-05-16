package com.vividseats.minivivid.fulfillment;

import com.vividseats.minivivid.common.checkout.PaymentCompleted;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class ShipItemProcessor {

    @Autowired
    Tracer tracer;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "Fulfillment.PaymentCompleted")
    public void shipItem(PaymentCompleted in) throws InterruptedException {
        System.out.println("Received " + in);

        Span serverSpan = tracer.activeSpan();
        Span span = tracer.buildSpan("shippingItem")
                .asChildOf(serverSpan.context())
                .start();

        try {
            Thread.sleep(100);
        } finally {
            span.finish();
        }

    }

}
