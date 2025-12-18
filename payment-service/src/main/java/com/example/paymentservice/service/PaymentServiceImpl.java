package com.example.paymentservice.service;

import com.example.paymentservice.dto.BalanceDto;
import com.example.paymentservice.entity.Balance;
import com.example.paymentservice.mapping.BalanceMapping;
import com.example.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import com.example.paymentservice.util.Constants;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final BalanceMapping balanceMapping;

    @Override
    public Mono<Long> save(BalanceDto balance) {
        Balance entity = balanceMapping.toEntity(balance);
        log.info("Balance was saved");
        return paymentRepository.save(entity)
                .map(e -> e.getId());
    }

    @Override
    public Mono<Boolean> isPositive(Long userId, BigDecimal amount) {
        return paymentRepository
                .findByUserId(userId)
                .doOnError(ex -> log.warn(String.format("User with ID {} is not found", userId)))
                .map(balance -> balance.getAmount().subtract(amount))
                .map(a -> a.compareTo(Constants.ZERO) >= 0);
    }
}