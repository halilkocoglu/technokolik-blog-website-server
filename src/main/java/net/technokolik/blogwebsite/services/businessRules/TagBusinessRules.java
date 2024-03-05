package net.technokolik.blogwebsite.services.businessRules;

import net.technokolik.blogwebsite.core.exception.BusinessException;
import net.technokolik.blogwebsite.core.messages.Messages;
import net.technokolik.blogwebsite.repositories.TagRepository;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TagBusinessRules {
    private final TagRepository tagRepository;

    public TagBusinessRules(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public void ifTagExistsShouldThrowException(String name){
        if(tagRepository.existsByName(name)){
            throw new BusinessException(Messages.getMessageForLocale("technokolik.constraint.tag.name.exists", LocaleContextHolder.getLocale()));
        }
    };

    public void ifTagNotExistShouldThrowException(String name) {
        if (!tagRepository.existsByName(name)){
            throw new BusinessException(Messages.getMessageForLocale("technokolik.constraint.tag.name.not_exists",LocaleContextHolder.getLocale()));
        }
    }
}
