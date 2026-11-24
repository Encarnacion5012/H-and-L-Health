package com.authservice.domain.model;

import com.github.f4b6a3.uuid.UuidCreator;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
@Getter

public class User {
 private UUID authUserId;
 private String username;
 private String password;
 private String email;
 private Boolean active;
 private Instant createdAt;
 private Instant desactivatedAt;
 private Instant activatedAt;
 private String profilePhoto;
 private Set<Integer> idsRoles;

    @Builder(toBuilder = true)
    private User(UUID authUserId, String username, String password, String email, Boolean active, Instant createdAt, Instant desactivatedAt, Instant activatedAt, String profilePhoto,  Set<Integer> idsRoles) {
        this.authUserId = (authUserId != null) ? authUserId : UuidCreator.getTimeOrderedEpoch();
        this.username = verifyUsername(username);
        this.password = verifyPassword(password);
        this.email = verifyEmail(email);
        this.active = (active != null) ? active : true;
        this.createdAt = (createdAt != null) ? createdAt : Instant.now();
        this.desactivatedAt = desactivatedAt;
        this.activatedAt = this.createdAt;
        this.profilePhoto = profilePhoto;
        this.idsRoles = verifyIdsRoles(idsRoles);
    }


    private String verifyPassword(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password length must be at least 8 characters");
        }

        if (password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be blank");
        }

        return password;
    }
    private String verifyEmail(String email) {
        if (email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be blank");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email must be valid");
        }
        return email;
    }
    private String verifyUsername(String username) {
        if (username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }

        if (username.length() < 8) {
            throw new IllegalArgumentException("Username length must be at least 8 characters");
        }
        return username;
    }

    private Set<Integer> verifyIdsRoles(Set<Integer> idsRoles) {
        if (idsRoles == null) {
            throw new IllegalArgumentException("Ids roles cannot be null");
        }

        return idsRoles;
    }
}
