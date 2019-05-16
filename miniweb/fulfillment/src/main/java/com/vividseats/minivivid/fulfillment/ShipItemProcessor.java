package com.vividseats.minivivid.fulfillment;

import com.vividseats.minivivid.common.checkout.PaymentCompleted;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class ShipItemProcessor {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "Fulfillment.PaymentCompleted")
    public void shipItem(PaymentCompleted in) throws InterruptedException {
        System.out.println("Received " + in);

        Thread.sleep(250);

//        UUID uuid = UUID.randomUUID();
//        String randomUUIDString = uuid.toString();
//
//        PaymentCompleted paymentCompeted = new PaymentCompleted(randomUUIDString, in.getOrderId());
//        System.out.println("Sending " + paymentCompeted);
//
//        rabbitTemplate.convertAndSend(FulfillmentApplication.PAYMENT_COMPLETED_TOPIC, "", paymentCompeted);
    }

}
