package com.kshrd.analysisservice;

import com.kshrd.analysisservice.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AnalysisConsumer {

    @RabbitListener(queues = RabbitMQConfig.ANALYSIS_QUEUE)
    public void consume(String message) {
        System.out.println("📊 [Analysis Service] " + message);
    }
}
