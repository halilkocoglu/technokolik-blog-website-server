package net.technokolik.blogwebsite.services.abstracts;

import net.technokolik.blogwebsite.entities.concretes.Category;
import net.technokolik.blogwebsite.services.dtos.category.requests.CreateCategoryRequest;
import net.technokolik.blogwebsite.services.dtos.category.requests.UpdateCategoryRequest;
import net.technokolik.blogwebsite.services.dtos.category.responses.GetAllCategories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    void add (CreateCategoryRequest categoryRequest);
    void update (UpdateCategoryRequest updateCategoryRequest);
    void delete (Long id);
    List<GetAllCategories> getAll();
    Page<GetAllCategories> getAllViaPage(Pageable pageable);
    Category getOriginalById(Long id);
    GetAllCategories getById(Long id);

}
