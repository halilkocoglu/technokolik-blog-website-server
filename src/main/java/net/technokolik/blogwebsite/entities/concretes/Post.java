package net.technokolik.blogwebsite.entities.concretes;

import jakarta.persistence.*;
import lombok.*;
import net.technokolik.blogwebsite.entities.abstracts.BaseEntity;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Table(name = "posts")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post extends BaseEntity {
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    @Column(name = "image_path")
    private String imagePath;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostTag> postTags;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<FavoritePost> favoritePosts;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<DislikePost> dislikePosts;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<PostAnalytic> postAnalytics;

}
