package teamproject.usedmarket.service;

import org.springframework.web.multipart.MultipartFile;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.repository.ItemUpdateDto;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item save(Item item, HttpSession session) throws IOException;

    void update(Long itemId, ItemUpdateDto updateParam) throws IOException;

    Optional<Item> findById(Long id);

    String findMemberNameBySellerMemberId(Long sellerMemberId, Long itemId);

    void incrementViewsCount(Long itemId);

    List<Item> findItems();

    void delete(Long itemId);

    List<Item> findItemsWithPaging(int page, int pageSize, String itemType, String regionId);

    int countItems();

    List<Item> findItemsSortedByRegistrationDate(int page, int pageSize, String itemType, String regionId);

    List<Item> findItemsSortedByViewsCount(int page, int pageSize, String itemType, String regionId);

    List<Item> findItemsSortedByLikesCount(int page, int pageSize, String itemType, String regionId);

}