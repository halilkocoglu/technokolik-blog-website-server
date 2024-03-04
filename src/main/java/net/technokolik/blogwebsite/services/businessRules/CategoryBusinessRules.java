package net.technokolik.blogwebsite.services.businessRules;

import net.technokolik.blogwebsite.core.exception.BusinessException;
import net.technokolik.blogwebsite.core.messages.Messages;
import net.technokolik.blogwebsite.repositories.CategoryRepository;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CategoryBusinessRules {
    private  final CategoryRepository categoryRepository;

    public CategoryBusinessRules(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void ifCategoryNameExistsShouldThrowException(String name) {
        if (categoryRepository.existsByName(name)){
            throw  new BusinessException(Messages.getMessageForLocale("technokolik.constraint.category.name.exists", LocaleContextHolder.getLocale()));
        }

    }
}
