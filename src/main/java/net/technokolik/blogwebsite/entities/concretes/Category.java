package net.technokolik.blogwebsite.entities.concretes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.technokolik.blogwebsite.entities.abstracts.BaseEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Table(name = "categories")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity {
    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "category")
    private List<Post> posts;
}
