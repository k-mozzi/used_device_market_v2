package teamproject.usedmarket.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import teamproject.usedmarket.domain.item.ItemLike;

@Mapper
public interface LikeMapper {

    void addInterest(ItemLike itemLike);

    void removeInterest(ItemLike itemLike);

    boolean existsByMemberIdAndItemId(@Param("memberId") Long memberId, @Param("itemId") Long itemId);

}
