package com.example.paymentservice.repository;

import com.example.paymentservice.entity.Balance;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PaymentRepository extends ReactiveCrudRepository<Balance, Long> {
    Mono<Balance> findByUserId(Long userId);

}
