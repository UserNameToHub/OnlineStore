package com.example.paymentservice.entity;

import lombok.*;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@Table(name = "balance")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Balance {
    @Id
    public Long id;

    public Long userId;

    public BigDecimal amount;
}
