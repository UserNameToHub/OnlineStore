package ru.yandex.practicum.onlinestore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yandex.practicum.onlinestore.dto.OrderDto;
import ru.yandex.practicum.onlinestore.service.OrderService;

import java.util.List;

@Controller("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/orders")
    public String getAll(Model model) {
        List<OrderDto> orders = orderService.getAll();

        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/orders/{id}")
    public String get(@PathVariable("id") Long id,
                      @RequestParam(name = "newOrder", defaultValue = "false") Boolean newOrder,
                      Model model) {
        OrderDto order = orderService.getById(id);

        model.addAttribute("order", order);
        model.addAttribute("newOrder", newOrder);

        return "order";
    }

    @PostMapping("/buy")
    public String create() {
        Long orderId = orderService.save();

        return String.format("redirect:/orders/%d?newOrder=true", orderId);
    }
}
