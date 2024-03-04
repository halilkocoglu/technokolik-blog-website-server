package net.technokolik.blogwebsite.services.businessRules;

import lombok.AllArgsConstructor;
import net.technokolik.blogwebsite.core.exception.BusinessException;
import net.technokolik.blogwebsite.core.messages.Messages;
import net.technokolik.blogwebsite.repositories.CommentRepository;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentBusinessRules {
    private final CommentRepository commentRepository;
    public void ifPostNotFoundShouldThrowException(Long id){};
    public void ifCommentNotFoundShouldThrowException(Long id){
        if(!commentRepository.existsById(id)){
            throw new BusinessException(Messages.getMessageForLocale("technokolik.constraint.comment.not_found", LocaleContextHolder.getLocale()));
        }
    }
}
