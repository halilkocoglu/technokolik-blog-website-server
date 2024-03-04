package net.technokolik.blogwebsite.services.concretes;

import net.technokolik.blogwebsite.core.exception.BusinessException;
import net.technokolik.blogwebsite.core.messages.Messages;
import net.technokolik.blogwebsite.core.utils.mapper.MapperService;
import net.technokolik.blogwebsite.entities.concretes.DislikeComment;
import net.technokolik.blogwebsite.repositories.DislikeCommentRepository;
import net.technokolik.blogwebsite.services.abstracts.DislikeCommentService;
import net.technokolik.blogwebsite.services.businessRules.CommentBusinessRules;
import net.technokolik.blogwebsite.services.businessRules.DislikeCommentBusinessRules;
import net.technokolik.blogwebsite.services.dtos.dislikeComment.requests.CreateDislikeCommentRequest;
import net.technokolik.blogwebsite.services.dtos.dislikeComment.requests.UpdateDislikeCommentRequest;
import net.technokolik.blogwebsite.services.dtos.dislikeComment.responses.GetAllDislikeComments;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DislikeCommentManager implements DislikeCommentService {
    private final DislikeCommentRepository dislikeCommentRepository;
    private final MapperService mapperService;
    private final CommentBusinessRules commentBusinessRules;
    private final DislikeCommentBusinessRules dislikeCommentBusinessRules;

    public DislikeCommentManager(DislikeCommentRepository dislikeCommentRepository, MapperService mapperService, CommentBusinessRules commentBusinessRules, DislikeCommentBusinessRules dislikeCommentBusinessRules) {
        this.dislikeCommentRepository = dislikeCommentRepository;
        this.mapperService = mapperService;
        this.commentBusinessRules = commentBusinessRules;
        this.dislikeCommentBusinessRules = dislikeCommentBusinessRules;
    }

    @Override
    public void add(CreateDislikeCommentRequest request) {
        commentBusinessRules.ifCommentNotFoundShouldThrowException(request.getCommentId());
        dislikeCommentBusinessRules.ifUserNotFoundShouldThrowException(request.getUserId());
        DislikeComment dislikeComment = mapperService.forRequest().map(request, DislikeComment.class);
        dislikeCommentRepository.save(dislikeComment);
    }
    @Override
    public void update(UpdateDislikeCommentRequest request) {
        commentBusinessRules.ifCommentNotFoundShouldThrowException(request.getCommentId());
        dislikeCommentBusinessRules.ifUserNotFoundShouldThrowException(request.getUserId());
        DislikeComment dislikeComment = this.getOriginalById(request.getId());
        mapperService.forRequest().map(request, DislikeComment.class);
        dislikeCommentRepository.save(dislikeComment);
    }

    @Override
    public void delete(Long id) {
        DislikeComment dislikeComment = this.getOriginalById(id);
        dislikeCommentRepository.delete(dislikeComment);
    }

    @Override
    public DislikeComment getOriginalById(Long id) {
        return dislikeCommentRepository.findById(id).orElseThrow(() ->
                new BusinessException(Messages.getMessageForLocale("technokolik.constraint.dislike_comment.invalid.id", LocaleContextHolder.getLocale())));
    }

    @Override
    public GetAllDislikeComments getById(Long id) {
        DislikeComment dislikeComment = this.getOriginalById(id);
        return mapperService.forResponse().map(dislikeComment, GetAllDislikeComments.class);
    }

    @Override
    public List<GetAllDislikeComments> getAllByUserId(Long id) {
        dislikeCommentBusinessRules.ifUserNotFoundShouldThrowException(id);
        List<DislikeComment> commentList = dislikeCommentRepository.findByUserId(id);
        return commentList
                .stream()
                .map(dislikeComment -> mapperService.forResponse().map(dislikeComment, GetAllDislikeComments.class))
                .toList();
    }

    @Override
    public List<GetAllDislikeComments> getAllByCommentId(Long id) {
        commentBusinessRules.ifCommentNotFoundShouldThrowException(id);
        List<DislikeComment> commentList = dislikeCommentRepository.findByCommentId(id);
        return commentList
                .stream()
                .map(dislikeComment -> mapperService.forResponse().map(dislikeComment, GetAllDislikeComments.class))
                .toList();
    }

    @Override
    public List<GetAllDislikeComments> getAll() {
        List<DislikeComment> commentList = dislikeCommentRepository.findAll();
        return commentList
                .stream()
                .map(dislikeComment -> mapperService.forResponse().map(dislikeComment, GetAllDislikeComments.class))
                .toList();
    }

    @Override
    public Page<GetAllDislikeComments> getAllViaPage(Pageable pageable) {
        return dislikeCommentRepository.findAll(pageable).map(dislikeComment ->  mapperService.forResponse().map(dislikeComment, GetAllDislikeComments.class));
    }
}
