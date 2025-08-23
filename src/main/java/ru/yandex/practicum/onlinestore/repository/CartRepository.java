package ru.yandex.practicum.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.yandex.practicum.onlinestore.entity.Item;

import java.util.List;

public interface CartRepository extends JpaRepository<Item, Long> {
    @Query("select i from Item i where i.count > 0")
    List<Item> findAll();
}
