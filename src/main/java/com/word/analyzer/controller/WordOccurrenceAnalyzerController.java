package com.word.analyzer.controller;

import com.word.analyzer.service.WordOccurrenceAnalyzer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/analyzer")
public class WordOccurrenceAnalyzerController {

    private final WordOccurrenceAnalyzer wordOccurrenceAnalyzer;

    public WordOccurrenceAnalyzerController(WordOccurrenceAnalyzer wordOccurrenceAnalyzer) {
        this.wordOccurrenceAnalyzer = wordOccurrenceAnalyzer;
    }

    @GetMapping(value = "/occurrences/{frequency}")
    public ResponseEntity<?> getWordOccurrences(@PathVariable int frequency) {
        return new ResponseEntity<>(Map.of("values", wordOccurrenceAnalyzer.findNthOccurrence(frequency)), HttpStatus.OK);
    }
}
