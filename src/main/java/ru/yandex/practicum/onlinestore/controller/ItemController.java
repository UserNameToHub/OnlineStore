package ru.yandex.practicum.onlinestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.onlinestore.enumiration.ActionConstant;

@Controller
@RequestMapping("/items/{id}")
public class ItemController {
    @GetMapping
    String get(@PathVariable("id") Long id, Model model) {
        return "item";
    }

    @PostMapping
    String create(@PathVariable("id") Long id, @RequestParam(value = "action") ActionConstant actionConstant) {
        return "redirect:/items/" + id;
    }
}
