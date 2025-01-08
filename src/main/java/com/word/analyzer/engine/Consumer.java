package com.word.analyzer.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.word.analyzer.util.AppConstants.GROUP_ID;
import static com.word.analyzer.util.AppConstants.TOPIC;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
    public void consume(String message) throws IOException {
        logger.info("#### -> Consumed message -> {}", message);
    }
}
