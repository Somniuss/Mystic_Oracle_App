package com.somniuss.oracle.mapper;

import org.springframework.stereotype.Component;

import com.somniuss.oracle.dto.TelegramUserDto;
import com.somniuss.oracle.dto.WebUserDto;
import com.somniuss.oracle.entity.User;

@Component
public class WebUserMapper implements UserMapper {

    @Override
    public WebUserDto toWebDto(User user) {
        if (user == null) return null;
        WebUserDto dto = new WebUserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }

    @Override
    public User fromWebDto(WebUserDto dto) {
        if (dto == null) return null;
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        return user;
    }

    @Override
    public TelegramUserDto toTelegramDto(User user) {
        throw new UnsupportedOperationException("Not supported in WebUserMapper");
    }

    @Override
    public User fromTelegramDto(TelegramUserDto dto) {
        throw new UnsupportedOperationException("Not supported in WebUserMapper");
    }
}
