package ru.yandex.practicum.onlinestore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.yandex.practicum.onlinestore.dto.UserDto;
import ru.yandex.practicum.onlinestore.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public Mono<Void> save(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }
}
