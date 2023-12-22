package teamproject.usedmarket.repository;

import teamproject.usedmarket.domain.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    public Item save(Item item);

    public Optional<Item> findByItemId(Long itemId);

    public String findMemberNameBySellerMemberId(Long sellerMemberId, Long itemId);

    public List<Item> findAll();

    public void update(Long itemId, ItemUpdateDto updateParam);

    void incrementViewsCount(Long itemId);

    public void delete(Long itemId);
}
