package com.kshrd.notificationservice;

import com.kshrd.notificationservice.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_QUEUE)
    public void consume(String message) {
        System.out.println("📢 [Notification Service] " + message);
    }
}
