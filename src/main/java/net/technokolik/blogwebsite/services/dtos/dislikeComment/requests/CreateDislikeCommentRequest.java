package net.technokolik.blogwebsite.services.dtos.dislikeComment.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateDislikeCommentRequest {
    @NotNull(message = "{technokolik.constraint.comment.user_id.notnull}")
    @Positive(message = "{technokolik.constraint.id.positive}")
    Long userId;
    @Positive(message = "{technokolik.constraint.id.positive}")
    @NotNull(message = "{technokolik.constraint.dislike_comment.comment_id.notnull}")
    Long commentId;
}
