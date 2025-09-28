package com.kshrd.billingservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String DIRECT_EXCHANGE = "order-direct-exchange";
    public static final String FANOUT_EXCHANGE = "order-fanout-exchange";
    public static final String BILLING_QUEUE = "order-billing-queue";
    public static final String ROUTING_KEY_DIRECT = "order.payment";

    @Bean
    public Queue billingQueue() {
        return new Queue(BILLING_QUEUE, true);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding directBinding(Queue billingQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(billingQueue).to(directExchange).with(ROUTING_KEY_DIRECT);
    }

    @Bean
    public Binding fanoutBinding(Queue billingQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(billingQueue).to(fanoutExchange);
    }
}
