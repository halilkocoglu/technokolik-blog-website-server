package net.technokolik.blogwebsite.services.abstracts;

import net.technokolik.blogwebsite.entities.concretes.DislikeComment;
import net.technokolik.blogwebsite.services.dtos.dislikeComment.requests.CreateDislikeCommentRequest;
import net.technokolik.blogwebsite.services.dtos.dislikeComment.requests.UpdateDislikeCommentRequest;
import net.technokolik.blogwebsite.services.dtos.dislikeComment.responses.GetAllDislikeComments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DislikeCommentService {
    void add(CreateDislikeCommentRequest request);
    void update(UpdateDislikeCommentRequest request);
    void delete(Long id);
    DislikeComment getOriginalById(Long id);
    GetAllDislikeComments getById(Long id);
    List<GetAllDislikeComments> getAllByUserId(Long id);
    List<GetAllDislikeComments> getAllByCommentId(Long id);
    List<GetAllDislikeComments> getAll();
    Page<GetAllDislikeComments> getAllViaPage(Pageable pageable);
}
