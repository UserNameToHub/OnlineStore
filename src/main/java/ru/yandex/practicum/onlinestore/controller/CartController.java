package ru.yandex.practicum.onlinestore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.yandex.practicum.onlinestore.service.CartService;
import ru.yandex.practicum.onlinestore.util.Util;

@Controller
@RequestMapping("/cart/items")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

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
}