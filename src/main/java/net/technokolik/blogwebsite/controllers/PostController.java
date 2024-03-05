package net.technokolik.blogwebsite.controllers;

import jakarta.validation.Valid;
import net.technokolik.blogwebsite.services.abstracts.PostService;
import net.technokolik.blogwebsite.services.dtos.post.requests.CreatePostRequest;
import net.technokolik.blogwebsite.services.dtos.post.requests.UpdatePostRequest;
import net.technokolik.blogwebsite.services.dtos.post.responses.GetAllPosts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public void add(@RequestBody @Valid CreatePostRequest request){
        postService.add(request);
    }
    @PutMapping("{id}")
    public void update(@RequestBody @Valid UpdatePostRequest request){
        postService.update(request);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id){
        postService.delete(id);
    }
    @GetMapping("{id}")
    public GetAllPosts getById(@PathVariable("id") Long id) {
        return postService.getById(id);
    }
    @GetMapping("getAll")
    public List<GetAllPosts> getAll(@RequestParam(required = false) Long user_id,@RequestParam(required = false) Long category_id){
        if (user_id!=null){
            return postService.getByUserId(user_id);
        } else if (category_id!=null) {
            return postService.getByCategoryId(category_id);
        }else {
            return postService.getAll();
        }
    }
    @GetMapping("getAllViaPage")
    public Page<GetAllPosts> getAllViaPage(Pageable pageable, @RequestParam(required = false) Long user_id, @RequestParam(required = false) Long category_id){
        if (user_id!= null){
            return postService.getByUserIdViaPage(pageable,user_id);
        } else if (category_id!=null) {
            return postService.getByCategoryIdViaPage(pageable,category_id);
        }else {
            return postService.getAllViaPage(pageable);
        }
    }
}
