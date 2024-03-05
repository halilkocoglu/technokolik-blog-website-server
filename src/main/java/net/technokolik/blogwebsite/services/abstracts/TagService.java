package net.technokolik.blogwebsite.services.abstracts;

import net.technokolik.blogwebsite.entities.concretes.Tag;
import net.technokolik.blogwebsite.services.dtos.tag.requests.CreateTagRequest;
import net.technokolik.blogwebsite.services.dtos.tag.requests.UpdateTagRequest;
import net.technokolik.blogwebsite.services.dtos.tag.responses.GetAllTags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    void add(CreateTagRequest request);
    void update(UpdateTagRequest request);
    void delete(Long id);
    Tag getOriginalById(Long id);
    GetAllTags getById(Long id);
    GetAllTags getByName(String name);
    List<GetAllTags> getAll();
    Page<GetAllTags> getAllViaPage(Pageable pageable);
}
