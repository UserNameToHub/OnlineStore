package ru.yandex.practicum.onlinestore.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.onlinestore.dto.CartDto;
import ru.yandex.practicum.onlinestore.entity.Item;
import ru.yandex.practicum.onlinestore.enumiration.ActionConstant;
import ru.yandex.practicum.onlinestore.mapper.CartDtoMapper;
import ru.yandex.practicum.onlinestore.repository.CartRepository;
import ru.yandex.practicum.onlinestore.service.CartService;
import ru.yandex.practicum.onlinestore.service.ItemService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    private final CartDtoMapper cartDtoMapper;

    private final ItemService itemService;

    @Override
    public CartDto getAll() {
        List<Item> items = cartRepository.findAll();
        log.info("Обновление товара в корзине");

        return cartDtoMapper.toDto(items);
    }

    @Override
    @Transactional
    public void update(Long id, ActionConstant action) {
        log.info("Попытка обновить товар с id {}", id);
        itemService.update(id, action);
    }
}