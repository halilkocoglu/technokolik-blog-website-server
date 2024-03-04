package net.technokolik.blogwebsite.services.dtos.comment.responses;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetAllComments {
    Long id;
    String content;
    Long userId;
    String userEmail;
    Long postId;
    LocalDate createdDate;
    LocalDate updatedDate;
}
