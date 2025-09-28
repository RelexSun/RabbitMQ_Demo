package com.kshrd.orderservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String TOPIC_EXCHANGE = "order-topic-exchange";
    public static final String FANOUT_EXCHANGE = "order-fanout-exchange";
    public static final String DIRECT_EXCHANGE = "order-direct-exchange";
    public static final String HEADERS_EXCHANGE = "order-headers-exchange";

    public static final String ROUTING_KEY_TOPIC = "order.created";
    public static final String ROUTING_KEY_DIRECT = "order.payment";

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(HEADERS_EXCHANGE);
    }
}
