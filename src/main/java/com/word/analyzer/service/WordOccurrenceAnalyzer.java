package com.word.analyzer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static com.word.analyzer.util.AppConstants.FOLDER_PATH;

@SuppressWarnings("resource")
@Service
public class WordOccurrenceAnalyzer {

    private static final Logger logger = LoggerFactory.getLogger(WordOccurrenceAnalyzer.class);

    public List<String> findNthOccurrence(int frequency) {

        Map<String, Integer> wordMap = new HashMap<>();
        //Get all the files
        List<Path> files = null;
        try {
            files = Files.walk(Paths.get(FOLDER_PATH))
                    .filter(Files::isRegularFile)
                    .toList();
        } catch (IOException e) {
            logger.error("#### -> Error occurred while reading files -> {}", e.getMessage());
        }

        //Read each file and store the frequency
        for (Path file : files) {
            try {
                Files.lines(file)
                        .flatMap(line -> Arrays.stream(line.split("\\W+")))
                        .filter(word -> !word.isEmpty())
                        .forEach(word -> wordMap.merge(word, 1, Integer::sum));
            } catch (IOException e) {
                logger.error("#### -> Error occurred while processing files -> {}", e.getMessage());
            }
        }
        logger.info("Words and their frequency: {}", wordMap);
        var wordsByFrequency = getWordsByFrequency(frequency, wordMap);
        logger.info("Nth occurrence of words: {}", wordsByFrequency);
        return wordsByFrequency;
    }

    private List<String> getWordsByFrequency(int frequency, Map<String, Integer> wordMap) {
        //handle order by grouping words by their frequency
        var wordsFrequency = wordMap.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())
                ));
        logger.info("list of words by frequency: {}", wordsFrequency);

        //validate frequency
        if (frequency <= 0 || frequency > wordsFrequency.size()) {
            throw new IllegalArgumentException("Invalid frequency");
        }

        return wordsFrequency.getOrDefault(frequency, Collections.emptyList());
    }

}
