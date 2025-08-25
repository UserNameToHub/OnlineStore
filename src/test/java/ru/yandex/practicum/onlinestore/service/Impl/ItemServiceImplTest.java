package ru.yandex.practicum.onlinestore.service.Impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.onlinestore.SpringBootPostgreSQLBase;
import ru.yandex.practicum.onlinestore.entity.Item;
import ru.yandex.practicum.onlinestore.enumiration.ActionConstant;
import ru.yandex.practicum.onlinestore.enumiration.SortConstant;
import ru.yandex.practicum.onlinestore.repository.ItemRepository;
import ru.yandex.practicum.onlinestore.service.ItemService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceImplTest extends SpringBootPostgreSQLBase {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    private static Item item;

    @BeforeAll
    static void startUp() {
        item = Item.builder()
                .title("some")
                .price(BigDecimal.valueOf(89000))
                .description("some")
                .imagePath("some")
                .count(0)
                .build();
    }

    @AfterAll
    static void afterAll() {
        postgres.close();
    }

    @Test
    void existsById() {
        Long id = itemService.getAll("", SortConstant.NO, 5, 1).get(0).getId();
        boolean false_ = itemService.existsById(Long.MAX_VALUE);
        boolean true_ = itemService.existsById(id);

        assertEquals(false_, false);
        assertEquals(true_,  true);
    }

    @Test
    void getAll() {
        List<Item> items = itemService.getAll("", SortConstant.NO, 5, 1);
        assertEquals(2, items.size());
    }

    @Test
    void whenIdIsCorrectThenWeGetItem() {
        Item byId = itemService.getById(1l);
        assertNotNull(byId);
    }

    @Test
    void whenActionIsPlusThenWeGetUpCount() {
        Item item = itemService.getById(1L);

        int index = 1;
        int max = 2;
        for (;index <= max; index++) {
            itemService.update(item.getId(), ActionConstant.PLUS);
        }

        Item updatedItem = itemService.getById(1L);

        assertEquals(2, updatedItem.getCount());
    }

    @Test
    void whenActionIsMinusThenWeGetDownCount() {
        Item item = itemService.getById(1L);
        itemService.update(item.getId(), ActionConstant.PLUS);
        assertEquals(1, itemService.getById(1L).getCount());

        itemService.update(1L, ActionConstant.MINUS);
        assertEquals(0, itemService.getById(1L).getCount());
    }

    @Test
    void whenActionIsDeleteThenWeGetZero() {
        Item item = itemService.getById(1L);

        itemService.update(item.getId(), ActionConstant.PLUS);
        itemService.update(item.getId(), ActionConstant.PLUS);
        assertEquals(4, itemService.getById(1L).getCount());

        itemService.update(1L, ActionConstant.DELETE);
        assertEquals(0, itemService.getById(1L).getCount());
    }
}