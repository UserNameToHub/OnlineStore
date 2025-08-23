package ru.yandex.practicum.onlinestore.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.onlinestore.common.BaseMapper;
import ru.yandex.practicum.onlinestore.dto.CartDto;
import ru.yandex.practicum.onlinestore.entity.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartDtoMapper implements BaseMapper<Item, CartDto> {

    @Override
    public CartDto toDto(List<Item> entities) {
        return CartDto.builder()
                .items(entities)
                .total(entities.stream()
                        .map(item -> BigDecimal.valueOf(item.getCount()).multiply(item.getPrice()))
                        .collect(Collectors.toList())
                        .stream()
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .isEmpty(entities.isEmpty())
                .build();
    }
}