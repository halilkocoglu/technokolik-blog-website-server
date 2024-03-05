package net.technokolik.blogwebsite.repositories;

import net.technokolik.blogwebsite.entities.concretes.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long id);
    Page<Post> findByUserId(Pageable pageable,Long id);
    List<Post> findByCategoryId(Long id);
    Page<Post> findByCategoryId(Pageable pageable,Long id);

}
