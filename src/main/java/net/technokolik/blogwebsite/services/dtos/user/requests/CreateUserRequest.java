package net.technokolik.blogwebsite.services.dtos.user.requests;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateUserRequest {
    @Size(min = 2, message = "{technokolik.constraint.name}")
    @NotBlank(message = "{technokolik.constraint.not_blank}")
    String firstname;
    @Size(min = 2, message = "{technokolik.constraint.name}")
    @NotBlank(message = "{technokolik.constraint.not_blank}")
    String lastname;
    @NotBlank(message = "{technokolik.constraint.not_blank}")
    @Email(message = "{technokolik.constraint.email}")
    String email;
    @NotBlank(message = "{technokolik.constraint.not_blank}")
    @Size(min = 8, max = 20, message = "{technokolik.constraint.password_length}")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "{technokolik.constraint.password_format}")
    String password;
    @NotNull(message = "{technokolik.constraint.birth.not_null}")
    @Past(message = "{technokolik.constraint.past}")
    LocalDate birthDate;
}
