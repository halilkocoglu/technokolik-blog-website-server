package net.technokolik.blogwebsite.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.technokolik.blogwebsite.services.abstracts.CategoryService;
import net.technokolik.blogwebsite.services.dtos.category.requests.CreateCategoryRequest;
import net.technokolik.blogwebsite.services.dtos.category.requests.UpdateCategoryRequest;
import net.technokolik.blogwebsite.services.dtos.category.responses.GetAllCategories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping
    public  void add (@RequestBody @Valid CreateCategoryRequest request){
        categoryService.add(request);
    }
    @PutMapping("{id}")
    public void update (@RequestBody @Valid UpdateCategoryRequest request){
        categoryService.update(request);
    }
    @DeleteMapping("{id}")
    public void delete (@PathVariable("id") Integer id){
        categoryService.delete(id);
    }
    @GetMapping("{id}")
    public GetAllCategories getById(@RequestParam("id") Integer id){
        return categoryService.getById(id);
    }
    @GetMapping("viaPage")
    public Page<GetAllCategories> getAllViaPage(Pageable pageable){
        return categoryService.getAllViaPage(pageable);
    }
}
