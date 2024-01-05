package teamproject.usedmarket.repository;

import teamproject.usedmarket.domain.comment.Comment;

import java.util.List;

public interface CommentRepository {

    Comment getCommentById(int commentId);

    List<Comment> getCommentsByItemId(Long itemId);

    List<Comment> getRepliesByCommentId(int parentCommentId);

    Comment insertComment(Comment comment);

    void updateComment(int commentId, CommentUpdateDto updateParam);

    void deleteComment(int commentId);

}
