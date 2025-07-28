package ru.yandex.practicum.onlinestore.service;

import ru.yandex.practicum.onlinestore.common.BaseService;
import ru.yandex.practicum.onlinestore.entity.Item;
import ru.yandex.practicum.onlinestore.enumiration.ActionConstant;
import ru.yandex.practicum.onlinestore.enumiration.SortConstant;

import java.util.List;

/**
 *
 */
public interface ItemService extends BaseService<Long> {
    List<Item> getAll(String search, SortConstant sort, Integer pageSize, Integer pageNumber);

    void update(Long id, ActionConstant action);
}
