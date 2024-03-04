package net.technokolik.blogwebsite.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.technokolik.blogwebsite.services.abstracts.CommentService;
import net.technokolik.blogwebsite.services.dtos.comment.requests.CreateCommentRequest;
import net.technokolik.blogwebsite.services.dtos.comment.requests.UpdateCommentRequest;
import net.technokolik.blogwebsite.services.dtos.comment.responses.GetAllComments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comments")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping
    public void add(@RequestBody @Valid CreateCommentRequest request){
        commentService.add(request);
    }
    @PutMapping("{id}")
    public void update(@RequestBody @Valid UpdateCommentRequest request){
        commentService.update(request);
    }
    @DeleteMapping("{id}")
    public void delete (@PathVariable("id") Long id){commentService.delete(id);};
    @GetMapping("{id}")
    GetAllComments getById(@PathVariable Long id){return commentService.getById(id);}
    @GetMapping("getAll")
    public List<GetAllComments> getAll (@RequestParam(required = false) Long user_id, @RequestParam(required = false) Long post_id) {
        if(user_id!=null){
            return commentService.getByUserId(user_id);
        }else if (post_id!=null) {
            return commentService.getByPostId(post_id);
        }
        else {
            return commentService.getAll();
        }
    }
    @GetMapping("getAllViaPage")
    public Page<GetAllComments> getAllViaPage (Pageable pageable){
        return commentService.getAllViaPage(pageable);
    }
}
