package com.authservice.infrastructure.adapters.out.persistence.entity;

import com.authservice.domain.model.Role_Name;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private Role_Name roleName;
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    Set<UserEntity> users;


}
