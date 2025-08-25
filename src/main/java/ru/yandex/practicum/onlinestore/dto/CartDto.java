package ru.yandex.practicum.onlinestore.dto;

import lombok.Builder;
import lombok.Data;
import ru.yandex.practicum.onlinestore.entity.Item;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CartDto {
    private List<Item> items;

    private BigDecimal total;

    private Boolean isEmpty;
}
