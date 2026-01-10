package ru.yandex.practicum.onlinestore.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.yandex.practicum.onlinestore.entity.Role;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @PostMapping("/roles/{id}")
    public Mono<Void> create(@RequestBody Role role) {
        return null;
    }
}
