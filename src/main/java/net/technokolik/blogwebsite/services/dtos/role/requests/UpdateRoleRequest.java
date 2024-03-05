package net.technokolik.blogwebsite.services.dtos.role.requests;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateRoleRequest {
    @Positive(message = "{technokolik.constraint.id.positive}")
    Long id;
    @Size(min = 2, message = "{technokolik.constraint.name}" )
    String name;
}
