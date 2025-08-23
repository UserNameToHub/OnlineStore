package ru.yandex.practicum.onlinestore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.onlinestore.entity.Item;
import ru.yandex.practicum.onlinestore.enumiration.SortConstant;
import ru.yandex.practicum.onlinestore.mapper.PagingMapper;
import ru.yandex.practicum.onlinestore.service.ItemService;
import ru.yandex.practicum.onlinestore.util.Util;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final ItemService itemService;

    private final PagingMapper pagingMapper;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.PERMANENT_REDIRECT)
    public String getMain() {
        return "redirect:/main/items";
    }

    @GetMapping(value = "/main/items", produces = MediaType.TEXT_HTML_VALUE)
    public String get(@RequestParam(value = "search", defaultValue = "") String search,
                      @RequestParam(value = "sort", defaultValue = "NO") SortConstant sort,
                      @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                      Model model) {
        List<Item> items = itemService.getAll(search, sort, pageSize, pageNumber);
        model.addAttribute("items", items);
        model.addAttribute("paging", pagingMapper.toPagingDto(pageNumber, pageSize, items.size()));

        return "main";
    }

    @PostMapping("/main/items/{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam(value = "action") String action) {
        itemService.update(id, Util.getAction(action));
        return "redirect:/main/items";
    }
}