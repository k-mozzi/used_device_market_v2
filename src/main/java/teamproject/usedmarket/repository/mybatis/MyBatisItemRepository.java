package teamproject.usedmarket.repository.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.repository.ItemRepository;
import teamproject.usedmarket.repository.ItemUpdateDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public String findMemberNameBySellerMemberId(Long sellerMemberId, Long itemId) {
        log.info("sellerMemberId={}", sellerMemberId);
        return itemMapper.findMemberNameBySellerMemberId(sellerMemberId, itemId);
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        itemMapper.update(itemId, updateParam);
        log.info("update item={}", updateParam.getUpdateDatetime());
    }
@Override
    public void updateStatus(Long itemId, ItemUpdateDto updateParam) {
        itemMapper.updateStatus(itemId, updateParam);
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

    @Override
    public List<Item> findItemsWithPaging(int page, int pageSize, String itemType, String regionId) {
        int startRow = (page - 1) * pageSize;
        Map<String, Object> params = new HashMap<>();
        params.put("startRow", startRow);
        params.put("pageSize", pageSize);
        params.put("itemType", itemType);
        params.put("regionId", regionId);
        return itemMapper.findItemsWithPaging(params);
    }

    @Override
    public int countItems() {
        return itemMapper.countItems();
    }

    @Override
    public List<Item> findItemsSortedByRegistrationDate(int page, int pageSize, String itemType, String regionId) {
        int startRow = (page - 1) * pageSize;
        Map<String, Object> params = new HashMap<>();
        params.put("startRow", startRow);
        params.put("pageSize", pageSize);
        params.put("itemType", itemType);
        params.put("regionId", regionId);
        return itemMapper.findItemsSortedByRegistrationDate(params);
    }

    @Override
    public List<Item> findItemsSortedByViewsCount(int page, int pageSize, String itemType, String regionId) {
        int startRow = (page - 1) * pageSize;
        Map<String, Object> params = new HashMap<>();
        params.put("startRow", startRow);
        params.put("pageSize", pageSize);
        params.put("itemType", itemType);
        params.put("regionId", regionId);
        return itemMapper.findItemsSortedByViewsCount(params);
    }

    @Override
    public List<Item> findItemsSortedByLikesCount(int page, int pageSize, String itemType, String regionId) {
        int startRow = (page - 1) * pageSize;
        Map<String, Object> params = new HashMap<>();
        params.put("startRow", startRow);
        params.put("pageSize", pageSize);
        params.put("itemType", itemType);
        params.put("regionId", regionId);
        return itemMapper.findItemsSortedByLikesCount(params);
    }

    @Override
    public List<Item> findItemsWithPagingAndSearch(int page, int pageSize, String searchText) {
        // MyBatis에 전달할 맵 생성
        Map<String, Object> params = new HashMap<>();
        params.put("startRow", (page - 1) * pageSize);
        params.put("pageSize", pageSize);
        params.put("searchText", searchText);

        return itemMapper.findItemsWithPagingAndSearch(params);
    }

}