package teamproject.usedmarket.domain.item;

import lombok.Data;
import teamproject.usedmarket.domain.member.Member;

import java.util.Date;

/**
 * 댓글
 */
@Data
public class ItemComment {
    private int itemCommentId;
    private Long memberId;
    private Long itemId;
    private String message;
    private Date createDatetime;
    private Date updateDatetime;


    public ItemComment() {
    }

    public ItemComment(String message) {
        this.message = message;
    }
}
