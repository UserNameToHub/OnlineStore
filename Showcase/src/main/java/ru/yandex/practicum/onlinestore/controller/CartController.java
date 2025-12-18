package ru.yandex.practicum.onlinestore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.yandex.practicum.onlinestore.client.StoreClient;
import ru.yandex.practicum.onlinestore.service.CartService;
import ru.yandex.practicum.onlinestore.service.OrderService;
import ru.yandex.practicum.onlinestore.util.Constants;
import ru.yandex.practicum.onlinestore.util.Util;

import java.math.BigDecimal;

@Controller
@RequestMapping("/cart/items")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    private final OrderService orderService;

    private final StoreClient client;

    @GetMapping
    public Mono<String> get(Model model) {
        return cartService.getAll()
        .map(cartDto -> {
            model.addAttribute("items", cartDto.getItems());
            model.addAttribute("total", cartDto.getTotal());
            model.addAttribute("empty", cartDto.getIsEmpty());
            return "cart";
        });
    }

    @PostMapping("/{id}")
    public Mono<String> edit(@PathVariable("id") Long id, @RequestParam(value = "action") String action) {
        return cartService.update(id, Util.getAction(action))
                .thenReturn("redirect:/cart/items");
    }

    @PostMapping("/buy")
    public Mono<String> create(@PathVariable("balance") BigDecimal balance, Model model) {
        //TODO Добавить возможность создавать заказ под конкретного пользователя (spring security)
        if (client.execute(balance, 1L)) {
            return orderService.save()
                    .map(orderId -> String.format("redirect:/orders/%d?newOrder=true", orderId));
        } else {
            model.addAttribute("empty", true);
            model.addAttribute("message", Constants.MESSAGE);
            return Mono.just("cart");
        }
    }
}