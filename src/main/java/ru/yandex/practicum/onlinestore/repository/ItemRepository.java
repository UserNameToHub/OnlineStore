package ru.yandex.practicum.onlinestore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.yandex.practicum.onlinestore.entity.Item;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("select i from Item as i " +
            "where lower(i.title) like concat('%', lower(:search), '%') " +
            "or lower(i.description) like concat('%', lower(:search), '%')")
    Page<Item> findAll(String search, Pageable pageable);

    Optional<Item> findById(Long id);
}
