package com.prueba.capacitaciones.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(name = "is_admin", nullable = false)
    private boolean admin;

    @OneToMany(mappedBy = "user", orphanRemoval = false)
    @Builder.Default
    private Set<UserCourseProgressEntity> progresses = new HashSet<>();
}
