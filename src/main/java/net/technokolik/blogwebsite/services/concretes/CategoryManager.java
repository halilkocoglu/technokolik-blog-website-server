package net.technokolik.blogwebsite.services.concretes;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.technokolik.blogwebsite.core.exception.BusinessException;
import net.technokolik.blogwebsite.core.messages.Messages;
import net.technokolik.blogwebsite.core.utils.mapper.MapperService;
import net.technokolik.blogwebsite.entities.concretes.Category;
import net.technokolik.blogwebsite.repositories.CategoryRepository;
import net.technokolik.blogwebsite.services.abstracts.CategoryService;
import net.technokolik.blogwebsite.services.dtos.category.requests.CreateCategoryRequest;
import net.technokolik.blogwebsite.services.dtos.category.requests.UpdateCategoryRequest;
import net.technokolik.blogwebsite.services.dtos.category.responses.GetAllCategories;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final MapperService mapperService;

    @Override
    public void add(CreateCategoryRequest request) {

        Category category =mapperService.forRequest().map(request, Category.class);
        categoryRepository.save(category);
    }

    @Override
    public void update(UpdateCategoryRequest request) {
        Category category = this.getOriginalById(request.getId());

            mapperService.forRequest().map(request, Category.class);
            categoryRepository.save(category);
    }

    @Override
    public void delete(Integer id) {
        Category category = this.getOriginalById(id);
        categoryRepository.delete(category);
    }

    public Category getOriginalById(Integer id) {
        return  categoryRepository.findById(id).orElseThrow(() ->
                new BusinessException(Messages.getMessageForLocale("technokolik.constraint.category.invalid.id",
                        LocaleContextHolder.getLocale())));
    }

    @Override
    public GetAllCategories getById(Integer id) {
        Category category = this.getOriginalById(id);

        return mapperService.forResponse().map(category, GetAllCategories.class);
    }

    @Override
    public Page<GetAllCategories> getAllViaPage(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(category -> mapperService.forResponse().map(category, GetAllCategories.class));
    }
}
