package com.vividseats.minivivid.fulfillment;

import com.vividseats.minivivid.common.checkout.Order;
import com.vividseats.minivivid.common.checkout.PaymentCompleted;
import com.vividseats.minivivid.fulfillment.FulfillmentApplication;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@SuppressWarnings("unused")
public class ReserveItemProcessor {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "Fulfillment.NewOrders")
    public void reserveItem(Order in) throws InterruptedException {
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
