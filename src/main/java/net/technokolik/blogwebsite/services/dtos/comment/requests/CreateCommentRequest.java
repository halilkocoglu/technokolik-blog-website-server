package net.technokolik.blogwebsite.services.dtos.comment.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCommentRequest {
    @Size(max = 300, message = "{technokolik.constraint.comment.content.size}")
    String content;
    @NotNull(message = "{technokolik.constraint.comment.user_id.notnull}")
    Long userId;
    @NotNull(message = "{technokolik.constraint.comment.post_id.notnull}")
    Long postId;
}
