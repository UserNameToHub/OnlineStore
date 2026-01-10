package ru.yandex.practicum.onlinestore.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Flux;

@Table(name = "cart")
public class Cart {
    @Id
    private Long id;

    private Flux<Item> items;
}