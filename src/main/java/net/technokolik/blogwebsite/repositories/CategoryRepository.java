package net.technokolik.blogwebsite.repositories;

import net.technokolik.blogwebsite.entities.concretes.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
