package com.somniuss.oracle.telegram;

import com.somniuss.oracle.entity.Prediction;
import com.somniuss.oracle.service.PredictionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class OracleTelegramBot extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(OracleTelegramBot.class);

    private final PredictionService predictionService;
    private final String botUsername;
    private final String botToken;

    public OracleTelegramBot(
            PredictionService predictionService,
            @Value("${telegram.bot.username}") String botUsername,
            @Value("${telegram.bot.token}") String botToken
    ) {
        super(botToken);
        this.predictionService = predictionService;
        this.botUsername = botUsername;
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        logger.info("Received update: {}", update);

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText().trim().toLowerCase();
            long chatId = update.getMessage().getChatId();

            logger.info("Received message: '{}' from chat {}", messageText, chatId);

            String response;

            switch (messageText) {
                case "/start":
                    response = "Hello! I'm the Oracle. Type 'yes' to receive a prediction.";
                    break;
                case "yes":
                    response = getRandomPrediction();
                    break;
                default:
                    response = "I didn't understand that. Please type 'yes' to get a prediction.";
            }

            SendMessage message = SendMessage.builder()
                    .chatId(String.valueOf(chatId))
                    .text(response)
                    .build();

            try {
                execute(message);
            } catch (Exception e) {
                logger.error("Error while sending message to Telegram.", e);
            }
        }
    }

    private String getRandomPrediction() {
        try {
            Prediction prediction = predictionService.getRandomPrediction();
            return prediction.getText();
        } catch (Exception e) {
            logger.error("Error while getting prediction from service", e);
            return "An error occurred while retrieving a prediction.";
        }
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
