package net.technokolik.blogwebsite.entities.concretes;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.technokolik.blogwebsite.entities.abstracts.BaseEntity;
@EqualsAndHashCode(callSuper = true)
@Table(name = "favorite_posts")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoritePost extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
