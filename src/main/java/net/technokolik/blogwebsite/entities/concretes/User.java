package net.technokolik.blogwebsite.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.technokolik.blogwebsite.entities.abstracts.BaseEntity;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Table(name = "users")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "birth_date")
    private LocalDate birthDate;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FavoritePost> favoritePosts;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<DislikePost> dislikePosts;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FavoriteComment> favoriteComments;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<DislikeComment> dislikeComments;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserRole> userRoles;
}
