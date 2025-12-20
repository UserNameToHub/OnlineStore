package com.example.paymentservice.service;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface PaymentService {
    Mono<Long> save(BalanceDto balance);
    Mono<Boolean> isPositive(Long userId, BigDecimal amount);
}
