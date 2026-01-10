package ru.yandex.practicum.onlinestore.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.onlinestore.common.BaseMapperDto;
import ru.yandex.practicum.onlinestore.dto.UserDetailsDto;
import ru.yandex.practicum.onlinestore.entity.User;
import ru.yandex.practicum.onlinestore.repository.UserRoleRepository;

@Component
@RequiredArgsConstructor
public class UserDetailsMapper implements BaseMapperDto<User, UserDetailsDto> {
    private final UserRoleRepository repository;

    @Override
    public UserDetailsDto toDto(User entity) {
        return UserDetailsDto.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .authorities(repository.findAllRolesByUserId(entity.getId()))
                .build();
    }
}
