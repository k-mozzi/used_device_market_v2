package teamproject.usedmarket.repository.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import teamproject.usedmarket.domain.comment.Comment;
import teamproject.usedmarket.repository.CommentRepository;
import teamproject.usedmarket.repository.CommentUpdateDto;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisCommentRepository implements CommentRepository {

    private final CommentMapper commentMapper;

    @Override
    public Comment getCommentById(int commentId) {
        return commentMapper.getCommentById(commentId);
    }

    @Override
    public List<Comment> getCommentsByItemId(Long itemId) {
        return commentMapper.getCommentsByItemId(itemId);
    }

    @Override
    public List<Comment> getRepliesByParentCommentId(int parentCommentId) {
        return commentMapper.getRepliesByParentCommentId(parentCommentId);
    }

    @Override
    public Comment insertComment(Comment comment) {
        commentMapper.insertComment(comment);
        return comment;
    }

    @Override
    public Comment insertReply(Comment reply) {
        commentMapper.insertComment(reply);
        return reply;
    }

    @Override
    public void updateComment(int commentId, CommentUpdateDto updateParam) {
        commentMapper.updateComment(commentId, updateParam);
    }

    @Override
    public void deleteComment(int commentId) {
        commentMapper.deleteComment(commentId);
    }

    @Override
    public void deleteReply(int replyId) {
        commentMapper.deleteComment(replyId);
    }
}
