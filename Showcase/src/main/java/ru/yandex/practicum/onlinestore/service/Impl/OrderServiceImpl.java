package ru.yandex.practicum.onlinestore.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.yandex.practicum.onlinestore.dto.OrderDto;
import ru.yandex.practicum.onlinestore.entity.Item;
import ru.yandex.practicum.onlinestore.entity.Order;
import ru.yandex.practicum.onlinestore.mapper.OrderDtoMapper;
import ru.yandex.practicum.onlinestore.repository.CartRepository;
import ru.yandex.practicum.onlinestore.repository.OrderRepository;
import ru.yandex.practicum.onlinestore.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final CartRepository cartRepository;

    private final OrderDtoMapper orderDtoMapper;

    @Override
    @Transactional
    public Mono<Long> save() {
        return cartRepository.findAll()
                .collectList()
                .map(itemsInCar -> {
                    log.info("Количество товаров в корзине: {}", itemsInCar.size());
                    Order order = Order.builder()
                            .items(itemsInCar)
                            .build();
                    return order;
                })
                .flatMap(orderRepository::save)
                .doOnSuccess(savedOrder -> log.info("Заказ был сохранён"))
                .map(Order::getId);
    }

    @Override
    @Cacheable(
            value = "order",
            key = "#id"
    )
    public Mono<OrderDto> getById(Long id) {
        log.info("Получить заказ с id:: {}", id);

        return orderRepository.findById(id)
                .map(orderDtoMapper::toDto);
    }

    @Override
    @Cacheable(
            value = "order",
            key = "'all'"
    )
    public Flux<OrderDto> getAll() {
        return orderRepository.findAll()
                .map(orderDtoMapper::toDto);
    }
}