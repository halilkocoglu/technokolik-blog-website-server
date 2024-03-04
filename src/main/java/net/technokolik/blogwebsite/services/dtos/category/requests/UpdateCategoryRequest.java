package net.technokolik.blogwebsite.services.dtos.category.requests;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateCategoryRequest {
    @Positive(message = "{technokolik.constraint.id.positive}")
    private Long id;
    @Size(min = 2,message = "{technokolik.constraint.category.name.size}")
    private String name;
}
