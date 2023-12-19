package teamproject.usedmarket.domain.item;

import lombok.Data;
import teamproject.usedmarket.domain.member.Member;

import java.util.Date;

/**
 * 좋아요(찜 기능)
 */
@Data
public class ItemLike {
    private int itemLikeId;
    private Long memberId;
    private Long itemId;
    private Date createDatetime;
    private Date updateDatetime;


    public ItemLike() {
    }


}
