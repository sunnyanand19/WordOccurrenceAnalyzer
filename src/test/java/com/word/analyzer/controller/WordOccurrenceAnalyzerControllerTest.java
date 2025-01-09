package com.word.analyzer.controller;

import com.word.analyzer.service.WordOccurrenceAnalyzer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WordOccurrenceAnalyzerController.class)
class WordOccurrenceAnalyzerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WordOccurrenceAnalyzer wordOccurrenceAnalyzer;

    @Test
    void whenIllegalFrequencyThenThrowException() throws Exception {
        int frequency = -1;
        given(wordOccurrenceAnalyzer.findNthOccurrence(frequency))
                .willThrow(IllegalArgumentException.class);
        mockMvc.perform(get("/occurrences/" + frequency))
                .andExpect(status().isNotFound());

    }

    @Test
    void whenValidFrequencyThenReturnNthOccurrence() throws Exception {
        int frequency = 3;
        given(wordOccurrenceAnalyzer.findNthOccurrence(frequency))
                .willReturn(List.of("premise", "from", "Learners", "specific"));
        mockMvc.perform(get("/occurrences/").param("frequency", "3"))
                .andExpect(result -> result.getResponse().getContentAsString().contains("premise"));

    }

}