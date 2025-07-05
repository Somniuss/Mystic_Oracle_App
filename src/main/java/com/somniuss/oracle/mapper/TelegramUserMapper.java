package com.somniuss.oracle.mapper;

import org.springframework.stereotype.Component;

import com.somniuss.oracle.dto.TelegramUserDto;
import com.somniuss.oracle.dto.WebUserDto;
import com.somniuss.oracle.entity.User;

@Component
public class TelegramUserMapper implements UserMapper {

    @Override
    public TelegramUserDto toTelegramDto(User user) {
        if (user == null) return null;
        TelegramUserDto dto = new TelegramUserDto();
        dto.setTelegramId(user.getTelegramId());
        dto.setUsername(user.getUsername());
        return dto;
    }

    @Override
    public User fromTelegramDto(TelegramUserDto dto) {
        if (dto == null) return null;
        User user = new User();
        user.setTelegramId(dto.getTelegramId());
        user.setUsername(dto.getUsername());
        return user;
    }

    @Override
    public WebUserDto toWebDto(User user) {
        throw new UnsupportedOperationException("Not supported in TelegramUserMapper");
    }

    @Override
    public User fromWebDto(WebUserDto dto) {
        throw new UnsupportedOperationException("Not supported in TelegramUserMapper");
    }
}
