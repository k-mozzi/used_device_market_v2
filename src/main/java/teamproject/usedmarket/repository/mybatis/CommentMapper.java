package teamproject.usedmarket.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import teamproject.usedmarket.domain.comment.Comment;
import teamproject.usedmarket.repository.CommentUpdateDto;

import java.util.List;

@Mapper
public interface CommentMapper {

    Comment getCommentById(int commentId);

    List<Comment> getCommentsByItemId(Long itemId);

    List<Comment> getRepliesByCommentId(int parentCommentId);

    void insertComment(Comment comment);

    void updateComment(@Param("commentId") int commentId, @Param("updateParam") CommentUpdateDto updateParam);

    void deleteComment(int commentId);

}
