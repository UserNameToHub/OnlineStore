package ru.yandex.practicum.onlinestore.service;

import ru.yandex.practicum.onlinestore.dto.CartDto;
import ru.yandex.practicum.onlinestore.enumiration.ActionConstant;

public interface CartService {
    CartDto getAll();

    void update(Long id, ActionConstant action);
}
