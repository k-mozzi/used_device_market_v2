package teamproject.usedmarket.service;

import teamproject.usedmarket.domain.item.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item save(Item item);

    void update(Long itemId, Item updateParam);

    Optional<Item> findById(Long id);

    List<Item> findItems();
}
