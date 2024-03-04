package net.technokolik.blogwebsite.services.dtos.user.responses;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GetAllUsers {
    Long id;
    String firstname;
    String lastname;
    String email;
    String password;
    LocalDate birthDate;
}
