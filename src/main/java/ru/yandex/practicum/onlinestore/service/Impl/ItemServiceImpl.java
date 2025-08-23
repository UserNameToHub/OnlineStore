package ru.yandex.practicum.onlinestore.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.onlinestore.entity.Item;
import ru.yandex.practicum.onlinestore.enumiration.ActionConstant;
import ru.yandex.practicum.onlinestore.enumiration.SortConstant;
import ru.yandex.practicum.onlinestore.repository.ItemRepository;
import ru.yandex.practicum.onlinestore.service.ItemService;
import ru.yandex.practicum.onlinestore.util.Util;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public boolean existsById(Long aLong) {
        return itemRepository.existsById(aLong);
    }

    @Override
    public List<Item> getAll(String search, SortConstant sort, Integer pageSize, Integer pageNumber) {
        Sort sortedValue = Util.getSort(sort);
        PageRequest pr = Objects.isNull(sortedValue) ? PageRequest.of(pageNumber - 1, pageSize) :
                PageRequest.of(pageNumber - 1, pageSize, sortedValue);

        return itemRepository.findAll(search, pr).toList();
    }

    @Override
    public Item getById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Long id, ActionConstant action) {
        var itemOpt = itemRepository.findById(id);

        if (itemOpt.isPresent()) {
            var item = itemOpt.get();
            var count = itemOpt.get().getCount();

            var newCount = switch (action) {
                case PLUS -> ++count;
                case MINUS -> --count < 0 ? 0 : count;
                case DELETE -> 0;
            };

            item.setCount(newCount);
            itemRepository.save(item);
            log.info("Товаров в корзине после обновления на 1:: {}", itemRepository.findById(id).get().getCount());
        } else {
            log.warn("Товар с id {} не найден", id);
        }
    }
}