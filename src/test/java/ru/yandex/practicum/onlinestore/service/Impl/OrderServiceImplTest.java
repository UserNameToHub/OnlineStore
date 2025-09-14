package ru.yandex.practicum.onlinestore.service.Impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import ru.yandex.practicum.onlinestore.SpringBootPostgreSQLBase;
import ru.yandex.practicum.onlinestore.service.OrderService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest extends SpringBootPostgreSQLBase {
    @Autowired
    private OrderService orderService;

    @AfterEach
    void tearDown() {
        postgres.close();
    }

    @Test
    void save() {
        orderService.getAll()
                .collectList()
                .subscribe(orderDtos -> {
                    assertEquals(0, orderDtos.size());
                });

        Mono<Long> idSaved = orderService.save();
        orderService.getById(idSaved.block())
                .map(orderDto -> orderDto.getItems().size())
                .subscribe(i -> assertEquals(0, i));
    }

    @Test
    void getById() {
        Mono<Long> idSaved = orderService.save();
        orderService.getById(idSaved.block())
                .subscribe(i -> {
                    assertEquals(1L, i.getId());
                });
    }

    @Test
    void getAll() {
        orderService.save();
        orderService.save();

        orderService.getAll().map(orderDto -> orderDto.getItems().size())
                .subscribe(i -> assertEquals(2, i));

    }
}