package net.technokolik.blogwebsite.entities.abstracts;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@MappedSuperclass()
public abstract class BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_date")
    private LocalDate createdDate;
    @Column(name = "updated_date")
    private LocalDate updatedDate;
    @PrePersist
    private void beforeAdd () {
        createdDate = LocalDate.now();
    }
    @PreUpdate
    private void beforeUpdate () {
        updatedDate = LocalDate.now();
    }
}
