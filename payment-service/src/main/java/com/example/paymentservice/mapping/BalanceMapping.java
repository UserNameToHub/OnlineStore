package com.example.paymentservice.mapping;

import com.example.paymentservice.dto.BalanceDto;
import com.example.paymentservice.entity.Balance;
import org.springframework.stereotype.Component;

@Component
public class BalanceMapping {
    public Balance toEntity(BalanceDto dto) {
        return Balance.builder()
                .userId(dto.getUserId())
                .amount(dto.getAmount())
                .build();
    }
}