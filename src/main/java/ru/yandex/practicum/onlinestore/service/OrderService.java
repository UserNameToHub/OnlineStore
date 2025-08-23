package ru.yandex.practicum.onlinestore.service;


import ru.yandex.practicum.onlinestore.dto.OrderDto;

import java.util.List;

public interface OrderService {
    Long save();

    OrderDto getById(Long id);

    List<OrderDto> getAll();
}
