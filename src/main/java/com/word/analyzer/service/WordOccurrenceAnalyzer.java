package com.word.analyzer.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.word.analyzer.util.AppConstants.FOLDER_PATH;

@SuppressWarnings("resource")
@Service
public class WordOccurrenceAnalyzer {

    private Logger logger = Logger.getLogger(WordOccurrenceAnalyzer.class.getName());

    public List<String> findNthOccurrence(int frequency) {

        Map<String, Integer> wordMap = new HashMap<>();
        //Get all the files
        List<Path> files = null;
        try {
            files = Files.walk(Paths.get(FOLDER_PATH))
                    .filter(Files::isRegularFile)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Read each file and store the frequency
        for (Path file : files) {
            try {
                Files.lines(file)
                        .flatMap(line -> Arrays.stream(line.split("\\W+")))
                        .filter(word -> !word.isEmpty())
                        .forEach(word -> wordMap.merge(word, 1, Integer::sum));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        logger.info(String.format("Words and their frequency: %s", wordMap));
        return getWordsByFrequency(frequency, wordMap);
    }

    private List<String> getWordsByFrequency(int frequency, Map<String, Integer> wordMap) {
        //handle order by grouping words by their frequency
        var wordsFrequency = wordMap.entrySet().stream()
                 .collect(Collectors.groupingBy(Map.Entry::getValue,
                         Collectors.mapping(Map.Entry::getKey, Collectors.toList())
                 ));
        logger.info(String.format("list of words by frequency: %s", wordsFrequency));

        //validate frequency
        if(frequency <= 0 || frequency > wordsFrequency.size()){
            throw new IllegalArgumentException("Invalid frequency");
        }

        return wordsFrequency.getOrDefault(frequency, Collections.emptyList());
    }

}
