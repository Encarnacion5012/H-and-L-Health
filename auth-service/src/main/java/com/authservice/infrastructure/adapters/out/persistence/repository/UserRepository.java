package com.authservice.infrastructure.adapters.out.persistence.repository;

import com.authservice.infrastructure.adapters.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @EntityGraph(attributePaths = "roles")
    Optional<UserEntity> findByEmailAndActiveTrue(String email);
    Optional<UserEntity> findByAuthUserIdAndActiveTrue(UUID authUserId);
}
