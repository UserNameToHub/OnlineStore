package ru.yandex.practicum.onlinestore.service.Impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.onlinestore.SpringBootPostgreSQLBase;
import ru.yandex.practicum.onlinestore.dto.OrderDto;
import ru.yandex.practicum.onlinestore.repository.OrderRepository;
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
        int sizeOfOrders = orderService.getAll().size();
        assertEquals(0, sizeOfOrders);

        Long idSavedOrder = orderService.save();
        OrderDto orderDto = orderService.getById(idSavedOrder);
        int itemsSize = orderDto.getItems().size();
        assertEquals(0, itemsSize);
    }

    @Test
    void getById() {
        Long idSavedOrder = orderService.save();
        OrderDto order = orderService.getById(idSavedOrder);
        assertEquals(1L, order.getId());
    }

    @Test
    void getAll() {
        orderService.save();
        orderService.save();

        int oderSize = orderService.getAll().size();
        assertEquals(2, oderSize);
    }
}