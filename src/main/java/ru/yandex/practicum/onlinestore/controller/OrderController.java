package ru.yandex.practicum.onlinestore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;
import ru.yandex.practicum.onlinestore.service.OrderService;

@Controller("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/orders")
    public Mono<String> getAll(Model model) {
        return orderService.getAll()
                .collectList()
                .doOnNext(orders -> model.addAttribute("orders", orders))
                .thenReturn("orders");
    }

    @GetMapping("/orders/{id}")
    public Mono<String> get(@PathVariable("id") Long id,
                      @RequestParam(name = "newOrder", defaultValue = "false") Boolean newOrder,
                      Model model) {
        return orderService.getById(id)
                .map(order -> {
                    model.addAttribute("order", order);
                    model.addAttribute("newOrder", newOrder);
                    return "order";
                });
    }

    @PostMapping("/buy")
    public Mono<String> create() {
        return orderService.save()
                .map(orderId -> String.format("redirect:/orders/%d?newOrder=true", orderId));
    }
}