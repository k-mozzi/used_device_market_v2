package teamproject.usedmarket.service.item;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.repository.ItemRepository;
import teamproject.usedmarket.repository.ItemUpdateDto;
import teamproject.usedmarket.service.item.ItemService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ItemServiceV1 implements ItemService {


    private final ItemRepository itemRepository;


    @Override
    public Item save(Item item, HttpSession session) throws IOException {

        //세션에서 멤버 아이디 가져온 후 아이템 객체의 setSellerMemberId에 바인딩
        Long currentMemberId = (Long) session.getAttribute("memberId");
        item.setSellerMemberId(currentMemberId);

        Item savedItem = itemRepository.save(item);
        return savedItem;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) throws IOException {

        itemRepository.update(itemId, updateParam);

    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepository.findByItemId(id);
    }

    @Override
    public String findMemberNameBySellerMemberId(Long sellerMemberId, Long itemId) {
        return itemRepository.findMemberNameBySellerMemberId(sellerMemberId, itemId);
    }

    @Override
    public void incrementViewsCount(Long itemId) {
        Optional<Item> findItem = itemRepository.findByItemId(itemId);
        findItem.ifPresent(item -> {
            itemRepository.incrementViewsCount(itemId);
            item.setViewsCount(item.getViewsCount() + 1);
        });
    }

    @Override
    public void delete(Long itemId) {
        itemRepository.delete(itemId);
    }

    @Override
    public List<Item> findItemsWithPaging(int page, int pageSize, String itemType, String regionId) {
        return itemRepository.findItemsWithPaging(page, pageSize, itemType, regionId);
    }

    @Override
    public int countItems() {
        return itemRepository.countItems();
    }


    @Override
    public List<Item> findItemsSortedByRegistrationDate(int page, int pageSize, String itemType, String regionId) {
        return itemRepository.findItemsSortedByRegistrationDate(page, pageSize, itemType, regionId);
    }

    @Override
    public List<Item> findItemsSortedByViewsCount(int page, int pageSize, String itemType, String regionId) {
        return itemRepository.findItemsSortedByViewsCount(page, pageSize, itemType, regionId);
    }

    @Override
    public List<Item> findItemsSortedByLikesCount(int page, int pageSize, String itemType, String regionId) {
        return itemRepository.findItemsSortedByLikesCount(page, pageSize, itemType, regionId);
    }

    @Override
    public List<Item> findItemsWithPagingAndSearch(int page, int pageSize, String searchText) {
        return itemRepository.findItemsWithPagingAndSearch(page, pageSize, searchText);
    }



}