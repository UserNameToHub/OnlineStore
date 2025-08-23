package ru.yandex.practicum.onlinestore.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.onlinestore.common.BaseMapperDto;
import ru.yandex.practicum.onlinestore.dto.OrderDto;
import ru.yandex.practicum.onlinestore.entity.Order;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Component
public class OrderDtoMapper implements BaseMapperDto<Order, OrderDto> {
    @Override
    public OrderDto toDto(Order entity) {
        return OrderDto.builder()
                .id(entity.getId())
                .items(entity.getItems())
                .totalSum(entity.getItems().stream()
                        .map(item -> BigDecimal.valueOf(item.getCount()).multiply(item.getPrice()))
                        .collect(Collectors.toList())
                        .stream()
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .build();
    }
}
