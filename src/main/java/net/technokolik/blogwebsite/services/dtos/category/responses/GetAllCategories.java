package net.technokolik.blogwebsite.services.dtos.category.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCategories {
    private Integer id;
    private String name;
}
