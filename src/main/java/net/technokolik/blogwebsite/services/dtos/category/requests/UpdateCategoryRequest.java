package net.technokolik.blogwebsite.services.dtos.category.requests;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateCategoryRequest {
    private Integer id;
    @Size(min = 2,message = "{technokolik.constraint.category.name.size}")
    private String name;
}
