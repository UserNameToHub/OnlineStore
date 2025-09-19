package ru.yandex.practicum.onlinestore.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
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
    public Mono<CartDto> getAll() {
        return cartRepository.findAll()
                .collectList()
                .map(cartDtoMapper::toDto)
                .doOnSuccess(dto -> log.info("Обновление товара в корзине"));
    }

    @Override
    @Transactional
    public Mono<Void> update(Long id, ActionConstant action) {
        log.info("Попытка обновить товар с id {}", id);
        return itemService.update(id, action);
    }
}