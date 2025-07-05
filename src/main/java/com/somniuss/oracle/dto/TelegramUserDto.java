package com.somniuss.oracle.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class TelegramUserDto {

    @NotBlank(message = "Telegram ID is required")
    @Pattern(regexp = "\\d+", message = "Telegram ID must contain only digits")
    private String telegramId;

    private String username; 

    public TelegramUserDto() {}

    public TelegramUserDto(String telegramId, String username) {
        this.telegramId = telegramId;
        this.username = username;
    }

    public String getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(String telegramId) {
        this.telegramId = telegramId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
