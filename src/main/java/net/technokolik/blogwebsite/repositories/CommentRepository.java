package net.technokolik.blogwebsite.repositories;

import net.technokolik.blogwebsite.entities.concretes.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long id);
    List<Comment> findByUserId(Long id);
}
