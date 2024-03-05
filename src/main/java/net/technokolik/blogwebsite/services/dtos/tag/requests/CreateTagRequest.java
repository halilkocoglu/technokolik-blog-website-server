package net.technokolik.blogwebsite.services.dtos.tag.requests;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTagRequest {
    @Size(min = 2, message = "{technokolik.constraint.name}")
    String name;
}
