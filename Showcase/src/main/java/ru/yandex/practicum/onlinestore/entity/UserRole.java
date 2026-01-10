package ru.yandex.practicum.onlinestore.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
import ru.yandex.practicum.onlinestore.dto.UserRoleId;

@Data
@Table(name = "user_role")
public class UserRole {
    private UserRoleId id;
}
