package net.technokolik.blogwebsite.services.concretes;

import net.technokolik.blogwebsite.core.exception.BusinessException;
import net.technokolik.blogwebsite.core.messages.Messages;
import net.technokolik.blogwebsite.core.utils.mapper.MapperService;
import net.technokolik.blogwebsite.entities.concretes.Post;
import net.technokolik.blogwebsite.repositories.PostRepository;
import net.technokolik.blogwebsite.services.abstracts.PostService;
import net.technokolik.blogwebsite.services.businessRules.CategoryBusinessRules;
import net.technokolik.blogwebsite.services.businessRules.UserBusinessRules;
import net.technokolik.blogwebsite.services.dtos.post.requests.CreatePostRequest;
import net.technokolik.blogwebsite.services.dtos.post.requests.UpdatePostRequest;
import net.technokolik.blogwebsite.services.dtos.post.responses.GetAllPosts;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostManager implements PostService {
    private final PostRepository postRepository;
    private final MapperService mapperService;
    private final CategoryBusinessRules categoryBusinessRules;
    private final UserBusinessRules userBusinessRules;

    public PostManager(PostRepository postRepository, MapperService mapperService, CategoryBusinessRules categoryBusinessRules, UserBusinessRules userBusinessRules) {
        this.postRepository = postRepository;
        this.mapperService = mapperService;
        this.categoryBusinessRules = categoryBusinessRules;
        this.userBusinessRules = userBusinessRules;
    }

    @Override
    public void add(CreatePostRequest request) {
        categoryBusinessRules.ifCategoryIdNotExistShouldThrowException(request.getCategoryId());
        userBusinessRules.ifUserNotFoundShouldThrowException(request.getUserId());
        Post post = mapperService.forRequest().map(request, Post.class);
        postRepository.save(post);
    }

    @Override
    public void update(UpdatePostRequest request) {
        categoryBusinessRules.ifCategoryIdNotExistShouldThrowException(request.getCategoryId());
        Post post = this.getOriginalById(request.getId());
        mapperService.forRequest().map(request, Post.class);
        postRepository.save(post);
    }

    @Override
    public void delete(Long id) {
        Post post = this.getOriginalById(id);
        postRepository.delete(post);
    }

    @Override
    public Post getOriginalById(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new BusinessException(Messages.getMessageForLocale("technokolik.constraint.post.invalid.id", LocaleContextHolder.getLocale())));
    }

    @Override
    public GetAllPosts getById(Long id) {
        Post post = this.getOriginalById(id);
        return mapperService.forResponse().map(post, GetAllPosts.class);
    }

    @Override
    public List<GetAllPosts> getByUserId(Long id) {
        userBusinessRules.ifUserNotFoundShouldThrowException(id);
        List<Post> postList = postRepository.findByUserId(id);
        List<GetAllPosts> responseList = postList
                .stream()
                .map(post -> mapperService.forResponse().map(post, GetAllPosts.class))
                .toList();
        return responseList;
    }

    @Override
    public Page<GetAllPosts> getByUserIdViaPage(Pageable pageable, Long id) {
        userBusinessRules.ifUserNotFoundShouldThrowException(id);
        Page<Post> postList = postRepository.findByUserId(pageable,id);
        Page<GetAllPosts> responseList = postList.map(post -> mapperService.forResponse().map(post, GetAllPosts.class));
        return responseList;
    }

    @Override
    public List<GetAllPosts> getByCategoryId(Long id) {
        categoryBusinessRules.ifCategoryIdNotExistShouldThrowException(id);
        List<Post> postList = postRepository.findByCategoryId(id);
        return postList
                .stream()
                .map( post -> mapperService.forResponse().map(post, GetAllPosts.class))
                .toList();
    }

    @Override
    public Page<GetAllPosts> getByCategoryIdViaPage(Pageable pageable, Long id) {
        categoryBusinessRules.ifCategoryIdNotExistShouldThrowException(id);
        Page<Post> postList = postRepository.findByCategoryId(pageable, id);
        Page<GetAllPosts> responseList = postList.map(post -> mapperService.forResponse().map(post, GetAllPosts.class));
        return responseList;
    }

    @Override
    public List<GetAllPosts> getAll() {
        List<Post> postList = postRepository.findAll();
        return postList
                .stream()
                .map(post -> mapperService.forResponse().map(post,GetAllPosts.class) )
                .toList();
    }

    @Override
    public Page<GetAllPosts> getAllViaPage(Pageable pageable) {
        Page<Post> postList = postRepository.findAll(pageable);
        return postList.map(post -> mapperService.forResponse().map(post,GetAllPosts.class) );
    }
}
