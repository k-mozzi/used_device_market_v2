package teamproject.usedmarket.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemLike;

import java.util.List;

@Mapper
public interface LikeMapper {

    void addInterest(ItemLike itemLike);

    void removeInterest(ItemLike itemLike);

    boolean existsByMemberIdAndItemId(@Param("memberId") Long memberId, @Param("itemId") Long itemId);

    List<Item> findLikedItemByMemberId(Long memberId);

}
