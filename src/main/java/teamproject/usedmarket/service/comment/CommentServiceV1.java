package teamproject.usedmarket.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamproject.usedmarket.domain.comment.Comment;
import teamproject.usedmarket.repository.CommentRepository;
import teamproject.usedmarket.repository.CommentUpdateDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceV1 implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public Comment getCommentById(int commentId) {
        return commentRepository.getCommentById(commentId);
    }

    @Override
    public List<Comment> getCommentsByItemId(Long itemId) {
        return commentRepository.getCommentsByItemId(itemId);
    }

    @Override
    public List<Comment> getRepliesByParentCommentId(int parentCommentId) {
        return commentRepository.getRepliesByParentCommentId(parentCommentId);
    }

    @Override
    public Comment insertComment(Comment comment) {
        return commentRepository.insertComment(comment);
    }

    @Override
    public Comment insertReply(Comment reply) {
        return commentRepository.insertReply(reply);
    }

    @Override
    public void updateComment(int commentId, CommentUpdateDto updateParam) {
        commentRepository.updateComment(commentId, updateParam);
    }

    @Override
    public void deleteComment(int commentId) {
        commentRepository.deleteComment(commentId);
    }

    @Override
    public void deleteReply(int replyId) {
        commentRepository.deleteComment(replyId);
    }
}
