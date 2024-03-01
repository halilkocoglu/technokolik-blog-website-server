package net.technokolik.blogwebsite.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.technokolik.blogwebsite.entities.abstracts.BaseEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Table(name = "roles")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<UserRole> userRoles;
}
