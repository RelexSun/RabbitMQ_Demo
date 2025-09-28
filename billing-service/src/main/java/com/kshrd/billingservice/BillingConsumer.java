package com.kshrd.billingservice;

import com.kshrd.billingservice.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BillingConsumer {

    @RabbitListener(queues = RabbitMQConfig.BILLING_QUEUE)
    public void consume(String message) {
        System.out.println("💰 [Billing Service] " + message);
    }
}
