package net.technokolik.blogwebsite.services.dtos.category.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCategories {
    private Long id;
    private String name;
    LocalDate createdDate;
    LocalDate updatedDate;
}
