package net.technokolik.blogwebsite.controllers;

import jakarta.validation.Valid;
import net.technokolik.blogwebsite.services.abstracts.TagService;
import net.technokolik.blogwebsite.services.dtos.tag.requests.CreateTagRequest;
import net.technokolik.blogwebsite.services.dtos.tag.requests.UpdateTagRequest;
import net.technokolik.blogwebsite.services.dtos.tag.responses.GetAllTags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tags")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public void add (@RequestBody @Valid CreateTagRequest request){
        tagService.add(request);
    }
    @PutMapping("{id}")
    public void update (@RequestBody @Valid UpdateTagRequest request){
        tagService.update(request);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id){
        tagService.delete(id);
    }
    @GetMapping("{id}")
    public GetAllTags getById(@PathVariable("id") Long id){
        return tagService.getById(id);
    }
    @GetMapping
    public GetAllTags getByName(@RequestParam(required = false) String name){
        return tagService.getByName(name);
    }
    @GetMapping("getAll")
    public List<GetAllTags> getAll() {
        return tagService.getAll();
    }
    @GetMapping("getAllViaPage")
    public Page<GetAllTags> getAllViaPage(Pageable pageable) {
        return tagService.getAllViaPage(pageable);
    }


}
