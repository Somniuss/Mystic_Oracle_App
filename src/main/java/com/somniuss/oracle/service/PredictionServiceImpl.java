package com.somniuss.oracle.service;

import com.somniuss.oracle.entity.Prediction;
import com.somniuss.oracle.repository.PredictionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PredictionServiceImpl implements PredictionService {

    private final PredictionRepository predictionRepository;
    private final Random random = new Random();
    // Hello from GIT!

    public PredictionServiceImpl(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    @Override
    public Prediction getRandomPrediction() {
        List<Prediction> predictions = predictionRepository.findAll();
        if (predictions.isEmpty()) {
            throw new IllegalStateException("No predictions available");
        }
        int index = random.nextInt(predictions.size());
        return predictions.get(index);
    }
}
