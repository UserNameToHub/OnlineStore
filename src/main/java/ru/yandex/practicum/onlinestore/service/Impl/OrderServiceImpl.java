package ru.yandex.practicum.onlinestore.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public Long save() {
        List<Item> itemsInCar = cartRepository.findAll();
        log.info("Количество товаров в корзине {]", itemsInCar.size());
        Order order = Order.builder()
                .items(itemsInCar)
                .build();
        Order savedOrder = orderRepository.save(order);
        log.info("Заказ был сохранен");
        return savedOrder.getId();
    }

    @Override
    public OrderDto getById(Long id) {
        log.info("Получить заказ с id:: {}", id);

        Order order = orderRepository.findById(id).orElse(new Order());
        return orderDtoMapper.toDto(order);
    }

    @Override
    public List<OrderDto> getAll() {
        return orderRepository.findAll().stream()
                .map(orderDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
