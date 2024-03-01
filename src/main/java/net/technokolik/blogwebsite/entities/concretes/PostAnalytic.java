package net.technokolik.blogwebsite.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.technokolik.blogwebsite.entities.abstracts.BaseEntity;
@EqualsAndHashCode(callSuper = true)
@Table(name = "post_analytics")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostAnalytic extends BaseEntity {
    @Column(name = "viewer_count")
    private Integer viewerCount;
    @Column(name = "favorite_count")
    private Integer favoriteCount;
    @Column(name = "dislike_count")
    private Integer dislikeCount;
    @Column(name = "comment_count")
    private Integer commentCount;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
