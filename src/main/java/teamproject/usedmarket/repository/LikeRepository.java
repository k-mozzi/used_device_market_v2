package teamproject.usedmarket.repository;

import teamproject.usedmarket.domain.item.ItemLike;

public interface LikeRepository {

    void addInterest(ItemLike itemLike);

    void removeInterest(ItemLike itemLike);

    boolean existsByMemberIdAndItemId(Long memberId, Long itemId);

}
