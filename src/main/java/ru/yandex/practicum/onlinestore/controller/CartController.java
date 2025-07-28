package ru.yandex.practicum.onlinestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.onlinestore.dto.CartDto;
import ru.yandex.practicum.onlinestore.enumiration.ActionConstant;

@Controller
@RequestMapping("/cart/items")
public class CartController {
    @GetMapping
    public String get(@ModelAttribute CartDto cartDto) {
        return "cart";
    }

    @PostMapping
    public String edit(@RequestParam(value = "action")ActionConstant action) {
        return "redirect:/main/items";
    }
}