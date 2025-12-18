package com.example.paymentservice.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Builder
@Component
public class BalanceDto {
    private Long userId;

    private BigDecimal amount;
}
