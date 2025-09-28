package com.kshrd.analysisservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String TOPIC_EXCHANGE = "order-topic-exchange";
    public static final String FANOUT_EXCHANGE = "order-fanout-exchange";
    public static final String HEADERS_EXCHANGE = "order-headers-exchange";

    public static final String ANALYSIS_QUEUE = "order-analysis-queue";
    public static final String ROUTING_KEY_TOPIC = "order.created";

    @Bean
    public Queue analysisQueue() {
        return new Queue(ANALYSIS_QUEUE, true);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(HEADERS_EXCHANGE);
    }

    @Bean
    public Binding topicBinding(Queue analysisQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(analysisQueue).to(topicExchange).with(ROUTING_KEY_TOPIC);
    }

    @Bean
    public Binding fanoutBinding(Queue analysisQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(analysisQueue).to(fanoutExchange);
    }

    @Bean
    public Binding headersBinding(Queue analysisQueue, HeadersExchange headersExchange) {
        return BindingBuilder.bind(analysisQueue).to(headersExchange).where("format").matches("pdf");
    }
}
