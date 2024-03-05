package net.technokolik.blogwebsite.services.dtos.role.responses;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GetAllRoles {
    Long id;
    String name;
}
