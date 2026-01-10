package ru.yandex.practicum.onlinestore.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRoleId {
    private Long userId;
    private Long passwordId;
}
