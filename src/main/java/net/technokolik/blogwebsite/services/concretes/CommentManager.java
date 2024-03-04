package net.technokolik.blogwebsite.services.concretes;

import lombok.AllArgsConstructor;
import net.technokolik.blogwebsite.core.exception.BusinessException;
import net.technokolik.blogwebsite.core.messages.Messages;
import net.technokolik.blogwebsite.core.utils.mapper.MapperService;
import net.technokolik.blogwebsite.entities.concretes.Comment;
import net.technokolik.blogwebsite.repositories.CommentRepository;
import net.technokolik.blogwebsite.services.abstracts.CommentService;
import net.technokolik.blogwebsite.services.businessRules.CommentBusinessRules;
import net.technokolik.blogwebsite.services.businessRules.UserBusinessRules;
import net.technokolik.blogwebsite.services.dtos.comment.requests.CreateCommentRequest;
import net.technokolik.blogwebsite.services.dtos.comment.requests.UpdateCommentRequest;
import net.technokolik.blogwebsite.services.dtos.comment.responses.GetAllComments;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CommentManager implements CommentService {
    private final CommentRepository commentRepository;
    private final MapperService mapperService;
    private final CommentBusinessRules commentBusinessRules;
    private final UserBusinessRules userBusinessRules;


    @Override
    public void add(CreateCommentRequest request) {
        userBusinessRules.ifUserNotFoundShouldThrowException(request.getUserId());
        commentBusinessRules.ifPostNotFoundShouldThrowException(request.getPostId());
        Comment comment = mapperService.forRequest().map(request, Comment.class);
        commentRepository.save(comment);

    }

    @Override
    public void update(UpdateCommentRequest request) {
        userBusinessRules.ifUserNotFoundShouldThrowException(request.getUserId());
        commentBusinessRules.ifPostNotFoundShouldThrowException(request.getPostId());
        Comment comment = this.getOriginalById(request.getId());
        mapperService.forRequest().map(request, Comment.class);
        commentRepository.save(comment);

    }

    @Override
    public void delete(Long id) {
        Comment comment = this.getOriginalById(id);
        commentRepository.delete(comment);

    }

    @Override
    public Comment getOriginalById(Long id) {

        return commentRepository.findById(id).orElseThrow(() ->
            new BusinessException(Messages.getMessageForLocale("technokolik.constraint.comment.invalid.id", LocaleContextHolder.getLocale()))
                );
    }

    @Override
    public GetAllComments getById(Long id) {
        Comment comment = this.getOriginalById(id);

        return mapperService.forResponse().map(comment, GetAllComments.class);
    }

    @Override
    public List<GetAllComments> getByPostId(Long id) {
        commentBusinessRules.ifPostNotFoundShouldThrowException(id);
        List<Comment> commentList = commentRepository.findByPostId(id);
        return commentList.stream().map(comment -> mapperService.forResponse().map(comment, GetAllComments.class)).toList();
    }

    @Override
    public List<GetAllComments> getByUserId(Long id) {
        userBusinessRules.ifUserNotFoundShouldThrowException(id);
        List<Comment> commentList = commentRepository.findByUserId(id);
        return commentList.stream().map(comment -> mapperService.forResponse().map(comment, GetAllComments.class)).toList();
    }

    @Override
    public List<GetAllComments> getAll() {
        List<Comment> commentList = commentRepository.findAll();
        List<GetAllComments> responseList = commentList.stream().map(comment -> mapperService.forResponse().map(comment, GetAllComments.class)).toList();
        return responseList;
    }

    @Override
    public Page<GetAllComments> getAllViaPage(Pageable pageable) {
        return commentRepository.findAll(pageable).map(comment -> mapperService.forResponse().map(comment, GetAllComments.class)) ;
    }
}
