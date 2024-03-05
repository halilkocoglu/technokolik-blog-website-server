package net.technokolik.blogwebsite.services.concretes;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.technokolik.blogwebsite.core.exception.BusinessException;
import net.technokolik.blogwebsite.core.messages.Messages;
import net.technokolik.blogwebsite.core.utils.mapper.MapperService;
import net.technokolik.blogwebsite.entities.concretes.Category;
import net.technokolik.blogwebsite.repositories.CategoryRepository;
import net.technokolik.blogwebsite.services.abstracts.CategoryService;
import net.technokolik.blogwebsite.services.businessRules.CategoryBusinessRules;
import net.technokolik.blogwebsite.services.dtos.category.requests.CreateCategoryRequest;
import net.technokolik.blogwebsite.services.dtos.category.requests.UpdateCategoryRequest;
import net.technokolik.blogwebsite.services.dtos.category.responses.GetAllCategories;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryManager implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final MapperService mapperService;
    private  final CategoryBusinessRules categoryBusinessRules;

    public CategoryManager(CategoryRepository categoryRepository, MapperService mapperService, CategoryBusinessRules categoryBusinessRules) {
        this.categoryRepository = categoryRepository;
        this.mapperService = mapperService;
        this.categoryBusinessRules = categoryBusinessRules;
    }

    @Override
    public void add(CreateCategoryRequest request) {
        categoryBusinessRules.ifCategoryNameExistsShouldThrowException(request.getName());
        Category category =mapperService.forRequest().map(request, Category.class);
        categoryRepository.save(category);
    }

    @Override
    public void update(UpdateCategoryRequest request) {
        categoryBusinessRules.ifCategoryNameExistsShouldThrowException(request.getName());
        Category category = this.getOriginalById(request.getId());
        mapperService.forRequest().map(request, Category.class);
        categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        Category category = this.getOriginalById(id);
        categoryRepository.delete(category);
    }

    @Override
    public GetAllCategories getById(Long id) {
        Category category = this.getOriginalById(id);

        return mapperService.forResponse().map(category, GetAllCategories.class);
    }

    @Override
    public List<GetAllCategories> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        List<GetAllCategories> responseList =categoryList
                .stream()
                .map(category -> mapperService.forResponse().map(category, GetAllCategories.class))
                .toList();
        return responseList;
    }

    @Override
    public Page<GetAllCategories> getAllViaPage(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(category -> mapperService.forResponse().map(category, GetAllCategories.class));
    }

    public Category getOriginalById(Long id) {
        return  categoryRepository.findById(id).orElseThrow(() ->
                new BusinessException(Messages.getMessageForLocale("technokolik.constraint.category.invalid.id",
                        LocaleContextHolder.getLocale())));
    }
}
