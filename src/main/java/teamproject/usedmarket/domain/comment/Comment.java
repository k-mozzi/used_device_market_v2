package teamproject.usedmarket.domain.comment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    public Comment(Long itemId, Long memberId, String content) {
        this.itemId = itemId;
        this.memberId = memberId;
        this.content = content;
    }
}
