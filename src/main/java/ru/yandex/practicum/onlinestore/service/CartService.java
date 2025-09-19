package ru.yandex.practicum.onlinestore.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.yandex.practicum.onlinestore.dto.CartDto;
import ru.yandex.practicum.onlinestore.enumiration.ActionConstant;

public interface CartService {
    Mono<CartDto> getAll();

    Mono<Void> update(Long id, ActionConstant action);
}
