package teamproject.usedmarket.service;

import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemLike;

import java.util.List;

public interface LikeService {

    void addInterest(ItemLike itemLike);

    void removeInterest(ItemLike itemLike);

    boolean existsByMemberIdAndItemId(Long memberId, Long itemId);

    List<Item> findLikedItemByMemberId(Long memberId);

    int totalLikedItem(Long itemId);

//    List<Item> findLikedItemsWithPaging(Long memberId, int page, int pageSize);
//
//    int countItems(Long memberId);

}
