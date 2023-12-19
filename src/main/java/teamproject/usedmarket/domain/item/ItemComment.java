package teamproject.usedmarket.domain.item;

import lombok.Data;
import teamproject.usedmarket.domain.member.Member;

import java.util.Date;

/**
 * 댓글
 */
@Data
public class ItemComment {
    private Long itemCommentId;
    private Member member;
    private Item item;
    private String message;
    private Date regiDate;
    private Date updateDate;

    public ItemComment() {
    }

    public ItemComment(Member member, Item item, String message, Date regiDate, Date updateDate) {
        this.member = member;
        this.item = item;
        this.message = message;
        this.regiDate = regiDate;
        this.updateDate = updateDate;
    }
}
