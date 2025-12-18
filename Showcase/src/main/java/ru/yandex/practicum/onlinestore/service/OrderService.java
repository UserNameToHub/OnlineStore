package ru.yandex.practicum.onlinestore.service;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.yandex.practicum.onlinestore.dto.OrderDto;

import java.util.List;

public interface OrderService {
    Mono<Long> save();

    Mono<OrderDto> getById(Long id);

    Flux<OrderDto> getAll();
}
