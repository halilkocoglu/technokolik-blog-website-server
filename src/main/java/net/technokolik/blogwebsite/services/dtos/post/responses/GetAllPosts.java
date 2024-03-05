package net.technokolik.blogwebsite.services.dtos.post.responses;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class GetAllPosts {
    Long id;
    String title;
    String description;
    String content;
    String imagePath;
    Long categoryId;
    Long userId;
}
