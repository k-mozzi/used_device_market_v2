package teamproject.usedmarket.service.comment;

import org.apache.ibatis.annotations.Param;
import teamproject.usedmarket.domain.comment.Comment;
import teamproject.usedmarket.repository.CommentUpdateDto;

import java.util.List;

public interface CommentService {

    Comment getCommentById(int commentId);

    List<Comment> getCommentsByItemId(Long itemId);

    List<Comment> getRepliesByParentCommentId(int parentCommentId);

    Comment insertComment(Comment comment);

    Comment insertReply(Comment reply);

    void updateComment(int commentId, CommentUpdateDto updateParam);

    void deleteComment(int commentId);

    void deleteReply(int replyId);

}
