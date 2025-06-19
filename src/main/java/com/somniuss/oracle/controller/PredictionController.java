package com.somniuss.oracle.controller;

import com.somniuss.oracle.entity.Prediction;
import com.somniuss.oracle.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PredictionController {

    private final PredictionService predictionService;

    @Autowired
    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping("/prediction/generate")
    public String generatePrediction(Model model) {
        Prediction prediction = predictionService.getRandomPrediction();
        model.addAttribute("prediction", prediction);
        return "result"; 
    }
}
