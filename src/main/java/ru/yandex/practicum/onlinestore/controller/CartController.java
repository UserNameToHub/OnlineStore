package ru.yandex.practicum.onlinestore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.onlinestore.dto.CartDto;
import ru.yandex.practicum.onlinestore.service.CartService;
import ru.yandex.practicum.onlinestore.util.Util;

@Controller
@RequestMapping("/cart/items")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public String get(Model model) {
        CartDto itemsInCart = cartService.getAll();
        model.addAttribute("items", itemsInCart.getItems());
        model.addAttribute("total", itemsInCart.getTotal());
        model.addAttribute("empty", itemsInCart.getIsEmpty());

        return "cart";
    }

    @PostMapping("/{id}")
    public String edit(@PathVariable("id") Long id, @RequestParam(value = "action") String action) {
        cartService.update(id, Util.getAction(action));
        return "redirect:/cart/items";
    }
}