package com.somniuss.oracle.telegram;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfig {

    private static final Logger logger = LoggerFactory.getLogger(TelegramBotConfig.class);

    private final OracleTelegramBot oracleTelegramBot;

    @Autowired
    public TelegramBotConfig(OracleTelegramBot oracleTelegramBot) {
        this.oracleTelegramBot = oracleTelegramBot;
    }

    @PostConstruct
    public void registerBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(oracleTelegramBot);
            logger.info("Telegram bot successfully registered.");
        } catch (TelegramApiException e) {
            logger.error("Failed to register Telegram bot: {}", e.getMessage(), e);
        }
    }
}
