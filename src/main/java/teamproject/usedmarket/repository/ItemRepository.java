package teamproject.usedmarket.repository;

import teamproject.usedmarket.domain.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    public Item save(Item item);

    public Optional<Item> findByItemId(Long itemId);

    public List<Item> findAll();

    public void update(Long itemId, ItemUpdateDto updateParam);

    public void updateViewsCount(Long itemId, ViewsCountUpdateDto updateParam);

    public void delete(Long itemId);
}
