package com.example.paymentservice.controller;

import com.example.paymentservice.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/deposit")
    public void toUpDeposit(@RequestBody @Valid BalanceDto balanceDto) {
        paymentService.save(balanceDto);
    }

    @PostMapping("/pay/{id}?amount")
    public Mono<Boolean> isPositive(@PathVariable("id") Long id, @RequestParam(value = "amount", defaultValue = "0") BigDecimal amount) {
        return paymentService.isPositive(id, amount);
    }
}