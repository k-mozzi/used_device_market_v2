package teamproject.usedmarket.repository.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import teamproject.usedmarket.domain.item.ItemLike;
import teamproject.usedmarket.repository.LikeRepository;

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
}
