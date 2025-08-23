package ru.yandex.practicum.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.practicum.onlinestore.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
