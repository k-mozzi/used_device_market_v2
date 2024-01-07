package teamproject.usedmarket.domain.item;

import lombok.Data;
import lombok.NoArgsConstructor;
import teamproject.usedmarket.domain.member.Member;

import java.util.Date;

/**
 * 좋아요(찜 기능)
 */
@Data
@NoArgsConstructor
public class ItemLike {
    private int itemLikeId;
    private Long memberId;
    private Long itemId;

    public ItemLike(Long memberId, Long itemId) {
        this.memberId = memberId;
        this.itemId = itemId;
    }
}
