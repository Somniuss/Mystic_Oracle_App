package com.somniuss.oracle.mapper;

import com.somniuss.oracle.dto.TelegramUserDto;
import com.somniuss.oracle.dto.WebUserDto;
import com.somniuss.oracle.entity.User;

public interface UserMapper {
    TelegramUserDto toTelegramDto(User user);
    User fromTelegramDto(TelegramUserDto dto);

    WebUserDto toWebDto(User user);
    User fromWebDto(WebUserDto dto);
}
