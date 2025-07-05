package com.somniuss.oracle.telegram;

import com.somniuss.oracle.entity.Prediction;
import com.somniuss.oracle.repository.PredictionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;
import java.util.Random;

@Component
public class OracleTelegramBot extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(OracleTelegramBot.class);

    private final PredictionRepository predictionRepository;
    private final String botUsername;
    private final String botToken;

    public OracleTelegramBot(
            PredictionRepository predictionRepository,
            @Value("${telegram.bot.username}") String botUsername,
            @Value("${telegram.bot.token}") String botToken
    ) {
        super(botToken);
        this.predictionRepository = predictionRepository;
        this.botUsername = botUsername;
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        logger.info("Received update: {}", update);

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            logger.info("Received message: '{}' from chat {}", messageText, chatId);

            String response;

            if (messageText.equalsIgnoreCase("/start")) {
                response = "Hello! I'm the Oracle. Type 'yes' to receive a prediction.";
            } else if (messageText.equalsIgnoreCase("yes")) {
                response = getRandomPrediction();
            } else {
                response = "I didn't understand that. Please type 'yes' to get a prediction.";
            }

            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatId));
            message.setText(response);

            try {
                execute(message);
            } catch (Exception e) {
                logger.error("Error while sending message to Telegram.", e);
            }
        }
    }

    private String getRandomPrediction() {
        long count = predictionRepository.count();
        if (count == 0) {
            return "No predictions available.";
        }

        int index = new Random().nextInt((int) count);

        Optional<Prediction> predictionOpt = predictionRepository.findAll().stream()
                .skip(index)
                .findFirst();

        return predictionOpt.map(Prediction::getText)
                .orElse("An error occurred while retrieving a prediction.");
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
