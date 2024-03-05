package net.technokolik.blogwebsite.services.dtos.role.requests;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateRoleRequest {
    @Size(min = 2, message = "{technokolik.constraint.name}")
    String name;
}
