package teamproject.usedmarket.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamproject.usedmarket.domain.comment.Comment;
import teamproject.usedmarket.domain.item.Item;
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
    public List<Comment> getRepliesByCommentId(int parentCommentId) {
        return commentRepository.getRepliesByCommentId(parentCommentId);
    }

    @Override
    public Comment insertComment(Comment comment) {
        Comment savedComment = commentRepository.insertComment(comment);
        return savedComment;
    }

    @Override
    public void updateComment(int commentId, CommentUpdateDto updateParam) {
        commentRepository.updateComment(commentId, updateParam);
    }

    @Override
    public void deleteComment(int commentId) {
        commentRepository.deleteComment(commentId);
    }
}
