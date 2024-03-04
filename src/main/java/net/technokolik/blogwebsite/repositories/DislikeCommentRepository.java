package net.technokolik.blogwebsite.repositories;

import net.technokolik.blogwebsite.entities.concretes.DislikeComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DislikeCommentRepository extends JpaRepository<DislikeComment, Long> {
    List<DislikeComment> findByUserId(Long id);
    List<DislikeComment> findByCommentId(Long id);
}
