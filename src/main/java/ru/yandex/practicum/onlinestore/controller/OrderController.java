package ru.yandex.practicum.onlinestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/orders")
public class OrderController {
    public String getAll(Model model) {
        return "orders";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable("id") Long id,
                      @RequestParam(name = "newOrder", defaultValue = "false") Boolean newOrder,
                      Model model) {
        return "order";
    }
}
