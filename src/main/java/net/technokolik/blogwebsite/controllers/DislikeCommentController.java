package net.technokolik.blogwebsite.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.technokolik.blogwebsite.services.abstracts.DislikeCommentService;
import net.technokolik.blogwebsite.services.dtos.dislikeComment.requests.CreateDislikeCommentRequest;
import net.technokolik.blogwebsite.services.dtos.dislikeComment.requests.UpdateDislikeCommentRequest;
import net.technokolik.blogwebsite.services.dtos.dislikeComment.responses.GetAllDislikeComments;
import org.apache.catalina.LifecycleState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dislike-comments")
@AllArgsConstructor
public class DislikeCommentController {
    private final DislikeCommentService dislikeCommentService;
    @PostMapping
    public  void add (@RequestBody @Valid CreateDislikeCommentRequest request) {
        dislikeCommentService.add(request);
    }
    @PutMapping("{id}")
    public void update (@RequestBody @Valid UpdateDislikeCommentRequest request){
        dislikeCommentService.update(request);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id){
        dislikeCommentService.delete(id);
    }
    @GetMapping("{id}")
    public GetAllDislikeComments getById(@PathVariable("id") Long id){
        return dislikeCommentService.getById(id);
    }
    @GetMapping("getAll")
    public List<GetAllDislikeComments> getAll(@RequestParam(required = false) Long user_id, @RequestParam(required = false) Long comment_id){
        if (user_id!=null){
            return dislikeCommentService.getAllByUserId(user_id);
        } else if (comment_id != null) {
            return dislikeCommentService.getAllByCommentId(comment_id);
        } else {
            return dislikeCommentService.getAll();
        }
    }
    @GetMapping("getAllViaPage")
    public Page<GetAllDislikeComments> getAllViaPage(Pageable pageable) {
        return dislikeCommentService.getAllViaPage(pageable);
    }

}
