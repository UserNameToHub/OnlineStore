package ru.yandex.practicum.onlinestore.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.onlinestore.entity.Order;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
}
