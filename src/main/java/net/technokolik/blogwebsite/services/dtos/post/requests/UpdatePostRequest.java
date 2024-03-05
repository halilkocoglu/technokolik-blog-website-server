package net.technokolik.blogwebsite.services.dtos.post.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class UpdatePostRequest {
    @Positive(message = "{technokolik.constraint.id.positive}")
    Long id;
    @Size(min = 2, message = "{technokolik.constraint.title}")
    String title;
    @Size(min = 2, message = "{technokolik.constraint.desc}")
    String description;
    @Size(min = 300, message = "{technokolik.constraint.post.content.size}")
    String content;
    @Nullable
    String imagePath;
    @NotNull(message = "{technokolik.constraint.comment.category_id.notnull}")
    Long categoryId;
}
