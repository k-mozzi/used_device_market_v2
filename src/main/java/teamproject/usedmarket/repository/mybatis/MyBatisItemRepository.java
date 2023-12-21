package teamproject.usedmarket.repository.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.repository.ItemRepository;
import teamproject.usedmarket.repository.ItemUpdateDto;

import java.util.List;
import java.util.Optional;


@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisItemRepository implements ItemRepository {


    private final ItemMapper itemMapper;
    @Override
    public Item save(Item item) {
        log.info("itemMapper class={}", itemMapper.getClass());
        itemMapper.save(item);
        return item;
    }

    @Override
    public Optional<Item> findByItemId(Long itemId) {
        return itemMapper.findById(itemId);
    }

    @Override
    public List<Item> findAll() {
        return itemMapper.findAll();
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        itemMapper.update(itemId, updateParam);
        log.info("update item={}", updateParam.getUpdateDatetime());
    }
    @Override
    public void incrementViewsCount(Long itemId) {
        itemMapper.incrementViewsCount(itemId);
    }

    @Override
    public void delete(Long itemId) {
        itemMapper.delete(itemId);
    }






}