package ru.yandex.practicum.onlinestore.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.onlinestore.entity.Role;

@Repository
public interface RoleRepository extends ReactiveCrudRepository<Role, Long> {
}
