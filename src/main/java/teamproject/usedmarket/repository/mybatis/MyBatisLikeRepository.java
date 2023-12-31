package teamproject.usedmarket.repository.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemLike;
import teamproject.usedmarket.repository.LikeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisLikeRepository implements LikeRepository {

    private final LikeMapper likeMapper;

    @Override
    public void addInterest(ItemLike itemLike) {
        likeMapper.addInterest(itemLike);
    }

    @Override
    public void removeInterest(ItemLike itemLike) {
        likeMapper.removeInterest(itemLike);
    }

    @Override
    public boolean existsByMemberIdAndItemId(Long memberId, Long itemId) {
        return likeMapper.existsByMemberIdAndItemId(memberId, itemId);
    }

    @Override
    public List<Item> findLikedItemByMemberId(Long memberId) {
        return likeMapper.findLikedItemByMemberId(memberId);
    }

    @Override
    public int totalLikedItem(Long itemId) {
        return likeMapper.totalLikedItem(itemId);
    }

//    @Override
//    public List<Item> findLikedItemsWithPaging(Long memberId, int page, int pageSize) {
//        int startRow = (page - 1) * pageSize;
//        Map<String, Object> params = new HashMap<>();
//        params.put("startRow", startRow);
//        params.put("pageSize", pageSize);
//        return likeMapper.findLikedItemsWithPaging(memberId, params);
//    }
//
//    @Override
//    public int countItems(Long memberId) {
//        return likeMapper.countItems(memberId);
//    }

}
