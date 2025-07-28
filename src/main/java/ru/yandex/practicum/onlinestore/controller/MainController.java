package ru.yandex.practicum.onlinestore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.onlinestore.enumiration.ActionConstant;
import ru.yandex.practicum.onlinestore.enumiration.SortConstant;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String getMain() {
        return "redirect:/main/items";
    }

    @GetMapping("/main/items")
    public String get(@RequestParam(value = "search", defaultValue = "") String search,
                      @RequestParam(value = "sort", defaultValue = "NO") SortConstant sort,
                      @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return "main";
    }

    @PostMapping("/main/items/{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam(value = "cation") ActionConstant action) {
        return "redirect:/main/items";
    }
}