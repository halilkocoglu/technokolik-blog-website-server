package net.technokolik.blogwebsite.services.abstracts;

import net.technokolik.blogwebsite.entities.concretes.Comment;
import net.technokolik.blogwebsite.services.dtos.comment.requests.CreateCommentRequest;
import net.technokolik.blogwebsite.services.dtos.comment.requests.UpdateCommentRequest;
import net.technokolik.blogwebsite.services.dtos.comment.responses.GetAllComments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    void add(CreateCommentRequest request);
    void update(UpdateCommentRequest request);
    void delete(Long id);
    Comment getOriginalById(Long id);
    GetAllComments getById(Long id);
    List<GetAllComments> getByPostId(Long id);
    List<GetAllComments> getByUserId(Long id);
    List<GetAllComments> getAll();
    Page<GetAllComments> getAllViaPage(Pageable pageable);


}
