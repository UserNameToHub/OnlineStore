package ru.yandex.practicum.onlinestore.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;

@Data
@Table(name = "role")
public class Role implements GrantedAuthority {
    @Id
    private Long id;

    private String authority;
}
