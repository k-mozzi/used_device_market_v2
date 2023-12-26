package teamproject.usedmarket.service;

import teamproject.usedmarket.domain.item.ItemLike;

public interface LikeService {

    void addInterest(ItemLike itemLike);

    void removeInterest(ItemLike itemLike);

    boolean existsByMemberIdAndItemId(Long memberId, Long itemId);

}
