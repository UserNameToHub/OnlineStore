package ru.yandex.practicum.onlinestore.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.yandex.practicum.onlinestore.entity.Item;
import ru.yandex.practicum.onlinestore.enumiration.ActionConstant;
import ru.yandex.practicum.onlinestore.enumiration.SortConstant;
import ru.yandex.practicum.onlinestore.repository.ItemRepository;
import ru.yandex.practicum.onlinestore.service.ItemService;
import ru.yandex.practicum.onlinestore.util.Util;

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
    public Mono<Boolean> existsById(Long aLong) {
        return itemRepository.existsById(aLong);
    }

    @Override
    public Flux<Item> getAll(String search, SortConstant sort, Integer pageSize, Integer pageNumber) {
        Sort sortedValue = Util.getSort(sort);
        PageRequest pr = Objects.isNull(sortedValue) ? PageRequest.of(pageNumber - 1, pageSize) :
                PageRequest.of(pageNumber - 1, pageSize, sortedValue);

        return itemRepository.findAll(search, pr);
//        return itemRepository.findAll(search, pr).toList();
    }

    @Override
    public Mono<Item> getById(Long id) {
        return itemRepository.findById(id);
//        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public Mono<Void> update(Long id, ActionConstant action) {

         return itemRepository.findById(id)
                .flatMap(itemOut -> {
                    var count = itemOut.getCount();

                    var newCount = switch (action) {
                        case PLUS -> ++count;
                        case MINUS -> --count < 0 ? 0 : count;
                        case DELETE -> 0;
                    };

                    itemOut.setCount(newCount);
                    return itemRepository.save(itemOut);
                })
                 .doOnSuccess(item -> log.info("Товаров в корзине после обновления: {}", item.getCount()))
                 .then()
                 .doOnError(error -> log.warn("Ошибка при обновлении товара с id {}", id, error));
    }
}