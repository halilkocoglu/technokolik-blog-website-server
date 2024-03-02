package net.technokolik.blogwebsite.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.technokolik.blogwebsite.entities.abstracts.BaseEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Table(name = "tags")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag extends BaseEntity {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<PostTag> postTags;


}
