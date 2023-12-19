package teamproject.usedmarket.domain.item;

import lombok.Data;
import teamproject.usedmarket.domain.member.Member;

import java.util.Date;

/**
 * 좋아요(찜 기능)
 */
@Data
public class ItemLike {
    private Long itemLideId;
    private Member member;
    private Item item;
    private Date regiDate;
    private Date updateDate;

    public ItemLike() {
    }

    public ItemLike(Member member, Item item, Date regiDate, Date updateDate) {
        this.member = member;
        this.item = item;
        this.regiDate = regiDate;
        this.updateDate = updateDate;
    }
}
