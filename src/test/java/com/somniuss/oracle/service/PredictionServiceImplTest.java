package com.somniuss.oracle.service;

import com.somniuss.oracle.entity.Prediction;
import com.somniuss.oracle.repository.PredictionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PredictionServiceImplTest {

    private PredictionRepository predictionRepository;
    private PredictionServiceImpl predictionService;

    @BeforeEach
    void setUp() {
        predictionRepository = mock(PredictionRepository.class);
        predictionService = new PredictionServiceImpl(predictionRepository);
    }

    @Test
    void getRandomPrediction_ShouldReturnOnePrediction() {
        
        Prediction p1 = new Prediction();
        p1.setText("Yes");
        Prediction p2 = new Prediction();
        p2.setText("No");
        List<Prediction> predictions = Arrays.asList(p1, p2);

        when(predictionRepository.findAll()).thenReturn(predictions);

      
        Prediction result = predictionService.getRandomPrediction();

        
        assertNotNull(result);
        assertTrue(predictions.contains(result));
    }

    @Test
    void getRandomPrediction_ShouldThrowException_WhenNoPredictions() {
        
        when(predictionRepository.findAll()).thenReturn(Collections.emptyList());

        
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            predictionService.getRandomPrediction();
        });

        assertEquals("No predictions available", exception.getMessage());
    }
}
