package ru.yandex.practicum.onlinestore.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.onlinestore.entity.Item;
import ru.yandex.practicum.onlinestore.enumiration.ActionConstant;
import ru.yandex.practicum.onlinestore.enumiration.SortConstant;
import ru.yandex.practicum.onlinestore.repository.ItemRepository;
import ru.yandex.practicum.onlinestore.service.ItemService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiсeImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Item> getAll(String search, SortConstant sort, Integer pageSize, Integer pageNumber) {
        return null;
    }

    @Override
    public void update(Long id, ActionConstant action) {
        if (itemRepository.existsById(id)) {
            log.warn("Товар с id {} не найден", id);
        }


    }
}
