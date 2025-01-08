package com.word.analyzer.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.word.analyzer.util.AppConstants.TOPIC;

@Service
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);


    private final KafkaTemplate<String, String> kafkaTemplate;

    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        logger.info("#### -> Producing message -> {}", message);
        this.kafkaTemplate.send(TOPIC, message);
    }

}
