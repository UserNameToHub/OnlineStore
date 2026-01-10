package ru.yandex.practicum.onlinestore.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.onlinestore.common.BaseMapperDto;
import ru.yandex.practicum.onlinestore.dto.UserDto;
import ru.yandex.practicum.onlinestore.entity.User;

@Component
public class UserDtoMapper implements BaseMapperDto<User, UserDto> {
    @Override
    public User toEntity(UserDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }
}
