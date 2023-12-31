package teamproject.usedmarket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemLike;
import teamproject.usedmarket.repository.LikeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeServiceV1 implements LikeService {

    private final LikeRepository likeRepository;

    @Override
    public void addInterest(ItemLike itemLike) {
        likeRepository.addInterest(itemLike);
    }

    @Override
    public void removeInterest(ItemLike itemLike) {
        likeRepository.removeInterest(itemLike);
    }

    @Override
    public boolean existsByMemberIdAndItemId(Long memberId, Long itemId) {
        return likeRepository.existsByMemberIdAndItemId(memberId, itemId);
    }

    @Override
    public List<Item> findLikedItemByMemberId(Long memberId) {
        return likeRepository.findLikedItemByMemberId(memberId);
    }

    @Override
    public int totalLikedItem(Long itemId) {
        return likeRepository.totalLikedItem(itemId);
    }

//    @Override
//    public List<Item> findLikedItemsWithPaging(Long memberId, int page, int pageSize) {
//        return likeRepository.findLikedItemsWithPaging(memberId, page, pageSize);
//    }
//
//    @Override
//    public int countItems(Long memberId) {
//        return likeRepository.countItems(memberId);
//    }


}
