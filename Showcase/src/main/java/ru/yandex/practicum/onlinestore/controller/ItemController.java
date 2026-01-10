package ru.yandex.practicum.onlinestore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.yandex.practicum.onlinestore.service.ItemService;
import ru.yandex.practicum.onlinestore.util.Util;

@Controller
@RequestMapping("/items/{id}")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public Mono<String> get(@PathVariable("id") Long id, Model model) {
        return itemService.getById(id)
                .doOnNext(item -> model.addAttribute("item", item))
                .thenReturn("item");
    }

    @PostMapping
    public Mono<String> create(@PathVariable("id") Long id, @RequestParam(value = "action") String action) {
        return itemService.update(id, Util.getAction(action))
                .thenReturn("redirect:/items/" + id);
    }
}