package com.word.analyzer.controller;

import com.word.analyzer.engine.Producer;
import com.word.analyzer.service.WordOccurrenceAnalyzer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final Producer producer;
    private final WordOccurrenceAnalyzer wordOccurrenceAnalyzer;

    public KafkaController(Producer producer, WordOccurrenceAnalyzer wordOccurrenceAnalyzer) {
        this.producer = producer;
        this.wordOccurrenceAnalyzer = wordOccurrenceAnalyzer;
    }

    @PostMapping(value = "/publish")
    public ResponseEntity<?> sendMessageToKafkaTopic(@RequestParam("frequency") int frequency) {
        List<String> nthOccurrence = wordOccurrenceAnalyzer.findNthOccurrence(frequency);
        this.producer.sendMessage(String.valueOf(nthOccurrence));
        return new ResponseEntity<>(Map.of("message", "message published"), HttpStatus.OK);
    }
}
