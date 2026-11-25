package com.authservice.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @Column(name = "auth_user_id")
    private UUID authUserId;
    private String username;
    private String password;
    private String email;
    private Boolean active;
    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "desactivated_at")
    private Instant desactivatedAt;

    @Column(name = "actived_at")
    private Instant activatedAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "profile_photo")
    private String profilePhoto;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "auth_user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id") )
    private Set<RoleEntity> roles;



    public void desactivateUser() {
        this.active = false;
        this.updatedAt = Instant.now();
        this.desactivatedAt = Instant.now();
    }

    public void activateUser() {
        this.active = true;
        this.updatedAt = Instant.now();
        this.desactivatedAt = Instant.now();
    }

    public void relationshipHelperUserRoleEntity(Set<RoleEntity> newRoles) {
        if (newRoles == null || newRoles.isEmpty()) {
            return;
        }
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }
        this.roles.addAll(newRoles);
        newRoles.forEach(r -> {
            if (r.getUsers() != null) {
                r.getUsers().add(this);
            }
        });
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles == null) {
            return List.of();
        }
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRoleName().name()))
                .toList();
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
