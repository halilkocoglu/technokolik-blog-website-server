package net.technokolik.blogwebsite.services.dtos.dislikeComment.responses;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetAllDislikeComments {
    Long id;
    Long userId;
    Long commentId;
    LocalDate createdDate;
    LocalDate updatedDate;
}
