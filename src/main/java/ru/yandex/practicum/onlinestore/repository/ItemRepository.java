package ru.yandex.practicum.onlinestore.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.yandex.practicum.onlinestore.entity.Item;

@Repository
public interface ItemRepository extends R2dbcRepository<Item, Long> {
    @Query("select i from Item as i " +
            "where lower(i.title) like concat('%', lower(:search), '%') " +
            "or lower(i.description) like concat('%', lower(:search), '%')")
    Flux<Item> findAll(String search, Pageable pageable);

    Mono<Item> findById(Long id);
}
