package ru.yandex.practicum.onlinestore.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.yandex.practicum.onlinestore.common.BaseService;
import ru.yandex.practicum.onlinestore.entity.Item;
import ru.yandex.practicum.onlinestore.enumiration.ActionConstant;
import ru.yandex.practicum.onlinestore.enumiration.SortConstant;

import java.util.List;

/**
 *
 */
public interface ItemService extends BaseService<Long> {
    Flux<Item> getAll(String search, SortConstant sort, Integer pageSize, Integer pageNumber);

    Mono<Item> getById(Long id);

    Mono<Void> update(Long id, ActionConstant action);
}
