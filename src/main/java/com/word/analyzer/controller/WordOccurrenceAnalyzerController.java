package com.word.analyzer.controller;

import com.word.analyzer.service.WordOccurrenceAnalyzer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analyzer")
public class WordOccurrenceAnalyzerController {

    private final WordOccurrenceAnalyzer wordOccurrenceAnalyzer;

    public WordOccurrenceAnalyzerController(WordOccurrenceAnalyzer wordOccurrenceAnalyzer) {
        this.wordOccurrenceAnalyzer = wordOccurrenceAnalyzer;
    }

    @GetMapping(value = "/occurrences/{frequency}")
    public List<String> getWordOccurrences(@PathVariable int frequency) {
        return wordOccurrenceAnalyzer.findNthOccurrence(frequency);
    }
}
