package net.technokolik.blogwebsite.repositories;

import net.technokolik.blogwebsite.entities.concretes.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String name);

    boolean existsByName(String name);
}
