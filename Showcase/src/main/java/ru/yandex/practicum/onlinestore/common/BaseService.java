package ru.yandex.practicum.onlinestore.common;

import reactor.core.publisher.Mono;

/**
 *
 * @param <ID> - id
 */
public interface BaseService<ID> {
    Mono<Boolean> existsById(ID id);
}
