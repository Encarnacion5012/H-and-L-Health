package com.authservice.infrastructure.adapters.out.persistence.repository;

import com.authservice.domain.model.Role_Name;
import com.authservice.infrastructure.adapters.out.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    Set<RoleEntity> findAllByRoleIdIn(Collection<Integer> roleIds);
    Optional<RoleEntity> findByRoleName(Role_Name roleName);
}
