package ru.yandex.practicum.onlinestore.common;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @param <T> - type
 */
public interface BaseRepository<T> extends JpaRepository<T, Long> {
    @Override
    boolean existsById(Long aLong);
}
