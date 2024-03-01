package net.technokolik.blogwebsite.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.technokolik.blogwebsite.entities.abstracts.BaseEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Table(name = "comments")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity {
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<FavoriteComment> favoriteComments;
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<DislikeComment> dislikeComments;
}
