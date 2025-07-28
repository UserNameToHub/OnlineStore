package ru.yandex.practicum.onlinestore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import ru.yandex.practicum.onlinestore.common.BaseRepository;
import ru.yandex.practicum.onlinestore.entity.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, BaseRepository<Item> {
    @Query("select i from items as i " +
                    "where (:search is null or lower(i.title) like concat('%', lower(:search), '%')) " +
                    "or (:search is null or lower(i.description) like concat('%', lower(:search), '%'))")
    Page<Item> findAll(@Nullable String search, Pageable pageable);



}
