package ru.yandex.practicum.onlinestore.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.yandex.practicum.onlinestore.entity.Item;

@Repository
public interface CartRepository extends ReactiveCrudRepository<Item, Long> {
    @Query("select i from Item i where i.count > 0")
    Flux<Item> findAll();
}