package ru.yandex.practicum.onlinestore.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.yandex.practicum.onlinestore.entity.Role;

@Repository
public interface UserRoleRepository extends ReactiveCrudRepository<Role, Long> {
    @Query("SELECT * FROM USER_ROLE WHERE USER_ID = :userId")
    Flux<Role> findAllRolesByUserId(Long userId);
}
