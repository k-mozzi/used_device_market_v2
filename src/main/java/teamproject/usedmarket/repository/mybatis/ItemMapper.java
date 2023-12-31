package teamproject.usedmarket.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.repository.ItemUpdateDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface ItemMapper {
    void save(Item item);

    void update(@Param("itemId") Long itemId, @Param("updateParam") ItemUpdateDto updateParam);

    void updateStatus(@Param("itemId") Long itemId, @Param("updateParam") ItemUpdateDto updateParam);

    void incrementViewsCount(Long itemId);

    List<Item> findByBuyerId(@Param("buyerMemberId") Long buyerMemberId);

    Optional<Item> findById(Long id);

    String findMemberNameBySellerMemberId(@Param("sellerMemberId") Long sellerMemberId, @Param("itemId") Long itemId);

    void delete(Long itemId);

    int countItems();

    List<Item> findItemsWithPaging(Map<String, Object> params);

    List<Item> findItemsSortedByRegistrationDate(Map<String, Object> params);

    List<Item> findItemsSortedByViewsCount(Map<String, Object> params);

    List<Item> findItemsSortedByLikesCount(Map<String, Object> params);

    List<Item> findItemsWithPagingAndSearch(Map<String, Object> params);
}