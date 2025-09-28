package com.kshrd.orderservice;

import com.kshrd.orderservice.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderPublisher {
    private final RabbitTemplate rabbitTemplate;
    
    public void publishTopic(String orderId) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.TOPIC_EXCHANGE,
                RabbitMQConfig.ROUTING_KEY_TOPIC,
                "Topic Exchange: Order created " + orderId
        );
    }

    public void publishFanout(String orderId) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.FANOUT_EXCHANGE,
                "",
                "Fanout Exchange: Order created " + orderId
        );
    }

    public void publishDirect(String orderId) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.DIRECT_EXCHANGE,
                RabbitMQConfig.ROUTING_KEY_DIRECT,
                "Direct Exchange: Payment received for order " + orderId
        );
    }

    public void publishHeaders(String orderId) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("format", "pdf");

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.HEADERS_EXCHANGE,
                "",
                "Headers Exchange: Analysis report for order " + orderId,
                message -> {
                    message.getMessageProperties().getHeaders().putAll(headers);
                    return message;
                }
        );
    }
}
