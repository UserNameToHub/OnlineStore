package ru.yandex.practicum.onlinestore.service.Impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import ru.yandex.practicum.onlinestore.SpringBootPostgreSQLBase;
import ru.yandex.practicum.onlinestore.entity.Item;
import ru.yandex.practicum.onlinestore.enumiration.ActionConstant;
import ru.yandex.practicum.onlinestore.enumiration.SortConstant;
import ru.yandex.practicum.onlinestore.repository.ItemRepository;
import ru.yandex.practicum.onlinestore.service.ItemService;

import java.math.BigDecimal;

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
                itemService.getAll("", SortConstant.NO, 5, 1)
                .collectList()
                        .subscribe(items -> {
                            var item1 = items.get(0);
                            Mono<Boolean> falseMono = itemService.existsById(Long.MAX_VALUE);
                            Mono<Boolean> trueMono = itemService.existsById(item1.getId());

                            assertEquals(falseMono, false);
                            assertEquals(trueMono, true);
                        });
    }

    @Test
    void getAll() {
        Iterable<Item> items = itemService.getAll("", SortConstant.NO, 5, 1)
                .toIterable();
        itemService.getAll("", SortConstant.NO, 5, 1)
                .collectList()
                .subscribe(i -> assertEquals(i.size(), 1));
    }

    @Test
    void whenIdIsCorrectThenWeGetItem() {
        Mono<Item> itemMono = itemService.getById(1L);
        itemMono.subscribe(item1 -> assertEquals(1L, item1.getId()));
    }

    @Test
    void whenActionIsPlusThenWeGetUpCount() {
        Mono<Item> itemMono = itemService.getById(1L);
        itemMono.doOnNext(item1 -> {
            itemService.update(item1.getId(), ActionConstant.PLUS);

        }).subscribe(item1 -> {
            assertEquals(2, item1.getCount());
        });
    }
}