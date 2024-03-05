package net.technokolik.blogwebsite.services.abstracts;

import net.technokolik.blogwebsite.entities.concretes.Post;
import net.technokolik.blogwebsite.services.dtos.post.requests.CreatePostRequest;
import net.technokolik.blogwebsite.services.dtos.post.requests.UpdatePostRequest;
import net.technokolik.blogwebsite.services.dtos.post.responses.GetAllPosts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    void add(CreatePostRequest request);
    void update(UpdatePostRequest request);
    void delete (Long id);
    Post getOriginalById(Long id);
    GetAllPosts getById(Long id);
    List<GetAllPosts> getByUserId(Long id);
    Page<GetAllPosts> getByUserIdViaPage(Pageable pageable, Long id);
    List<GetAllPosts> getByCategoryId(Long id);
    Page<GetAllPosts> getByCategoryIdViaPage(Pageable pageable,Long id);
    List<GetAllPosts> getAll();
    Page<GetAllPosts> getAllViaPage(Pageable pageable);
}
