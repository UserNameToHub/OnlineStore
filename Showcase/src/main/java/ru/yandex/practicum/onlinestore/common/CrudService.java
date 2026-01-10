package ru.yandex.practicum.onlinestore.common;

import jakarta.annotation.Nullable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @param <T> - type
 * @param <ID> - id
 */
public interface CrudService<T, ID>{
    default Mono<Void> create(T obj) {
        return null;
    };

    default Mono<T> findById(ID id) {
        return null;
    }

    default Flux<T> findAll(@Nullable ID id) {
        return null;
    }

    default Mono<T> update(T obj) {
        return null;
    }

    default Mono<Void> delete(ID id) {
        return null;
    }
 }