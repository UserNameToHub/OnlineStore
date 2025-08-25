package ru.yandex.practicum.onlinestore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.onlinestore.entity.Item;
import ru.yandex.practicum.onlinestore.service.ItemService;
import ru.yandex.practicum.onlinestore.util.Util;

@Controller
@RequestMapping("/items/{id}")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    String get(@PathVariable("id") Long id, Model model) {
        Item item = itemService.getById(id);
        model.addAttribute("item", item);

        return "item";
    }

    @PostMapping
    String create(@PathVariable("id") Long id, @RequestParam(value = "action") String action) {
        itemService.update(id, Util.getAction(action));
        return "redirect:/items/" + id;
    }
}