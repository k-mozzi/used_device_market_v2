package teamproject.usedmarket.domain.comment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Comment {
    private int commentId;
    private Long itemId;
    private Long memberId;
    private String content;
    private Date createDatetime;
    private Date updateDatetime;
    private Integer parentCommentId;
    private List<Comment> replies;

    public Comment(Long itemId, Long memberId, String content, Date createDatetime) {
        this.itemId = itemId;
        this.memberId = memberId;
        this.content = content;
        this.createDatetime = createDatetime;
    }

    public Comment(Long itemId, Long memberId, String content, Date createDatetime, Integer parentCommentId) {
        this.itemId = itemId;
        this.memberId = memberId;
        this.content = content;
        this.createDatetime = createDatetime;
        this.parentCommentId = parentCommentId;
    }
}
