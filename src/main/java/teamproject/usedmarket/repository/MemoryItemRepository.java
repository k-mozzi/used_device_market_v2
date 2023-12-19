//package teamproject.usedmarket.repository;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Repository;
//import teamproject.usedmarket.domain.item.Item;
//
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Slf4j
//@Repository
//public class MemoryItemRepository implements ItemRepository {
//
//    private static final Map<Long, Item> store = new ConcurrentHashMap<>();
//    private static long sequence = 0L;
//
//    @Override
//    public Item save(Item item) {
//        item.setItemId(++sequence);
//        item.setRegiDate(new Date());
//        store.put(item.getItemId(), item);
//        return item;
//    }
//
//    @Override
//    public Optional<Item> findByItemId(Long itemId) {
//        return Optional.ofNullable(store.get(itemId));
//    }
//
//    @Override
//    public List<Item> findAll() {
//        return new ArrayList<>(store.values());
//    }
//
//    @Override
//    public void update(Long itemId, Item updateParam) {
//        Item findItem = findByItemId(itemId);
//        findItem.setItemName(updateParam.getItemName());
//        findItem.setPrice(updateParam.getPrice());
//        findItem.setCategory(updateParam.getCategory());
//        findItem.setSeller(updateParam.getSeller());
//        findItem.setStatus(updateParam.getStatus());
//        findItem.setRegiDate(new Date());
//    }
//
//    @Override
//    public void delete(Long itemId) {
//        store.remove(itemId);
//    }
//
//    public void clearStore() {
//        store.clear();
//    }
//}
