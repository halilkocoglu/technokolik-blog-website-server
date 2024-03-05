package net.technokolik.blogwebsite.services.concretes;

import net.technokolik.blogwebsite.core.exception.BusinessException;
import net.technokolik.blogwebsite.core.messages.Messages;
import net.technokolik.blogwebsite.core.utils.mapper.MapperService;
import net.technokolik.blogwebsite.entities.concretes.Tag;
import net.technokolik.blogwebsite.repositories.TagRepository;
import net.technokolik.blogwebsite.services.abstracts.TagService;
import net.technokolik.blogwebsite.services.businessRules.TagBusinessRules;
import net.technokolik.blogwebsite.services.dtos.tag.requests.CreateTagRequest;
import net.technokolik.blogwebsite.services.dtos.tag.requests.UpdateTagRequest;
import net.technokolik.blogwebsite.services.dtos.tag.responses.GetAllTags;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TagManager implements TagService {
    private final TagRepository tagRepository;
    private final MapperService mapperService;
    private final TagBusinessRules tagBusinessRules;

    public TagManager(TagRepository tagRepository, MapperService mapperService, TagBusinessRules tagBusinessRules) {
        this.tagRepository = tagRepository;
        this.mapperService = mapperService;
        this.tagBusinessRules = tagBusinessRules;
    }

    @Override
    public void add(CreateTagRequest request) {
        tagBusinessRules.ifTagExistsShouldThrowException(request.getName());
        Tag tag = mapperService.forRequest().map(request,Tag.class);
        tagRepository.save(tag);
    }

    @Override
    public void update(UpdateTagRequest request) {
        tagBusinessRules.ifTagExistsShouldThrowException(request.getName());
        Tag tag = this.getOriginalById(request.getId());
        mapperService.forRequest().map(request, Tag.class);
        tagRepository.save(tag);
    }

    @Override
    public void delete(Long id) {
        Tag tag = this.getOriginalById(id);
        tagRepository.delete(tag);
    }

    @Override
    public Tag getOriginalById(Long id) {
        return tagRepository.findById(id).orElseThrow(() ->
                new BusinessException(Messages.getMessageForLocale("technokolik.constraint.tag.invalid.id", LocaleContextHolder.getLocale())));
    }

    @Override
    public GetAllTags getById(Long id) {
        Tag tag = this.getOriginalById(id);
        return mapperService.forResponse().map(tag, GetAllTags.class);
    }

    @Override
    public GetAllTags getByName(String name) {
        tagBusinessRules.ifTagNotExistShouldThrowException(name);
        Tag tag = tagRepository.findByName(name);
        return mapperService.forResponse().map(tag, GetAllTags.class);
    }

    @Override
    public List<GetAllTags> getAll() {
        List<Tag> tagList = tagRepository.findAll();
        return tagList.stream().map(tag -> mapperService.forResponse().map(tag, GetAllTags.class)).toList();
    }

    @Override
    public Page<GetAllTags> getAllViaPage(Pageable pageable) {
        Page<Tag> tagList = tagRepository.findAll(pageable);
        return  tagList.map(tag -> mapperService.forResponse().map(tag, GetAllTags.class));
    }
}
