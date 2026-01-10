package ru.yandex.practicum.onlinestore.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
@Builder
public class UserDto {
    private String username;

    private String password;
}
