package com.authservice.infrastructure.adapters.out.persistence.mapper;

import com.authservice.aplication.dto.results.RoleResult;
import com.authservice.domain.model.Role;
import com.authservice.infrastructure.adapters.out.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolePersistenceMapper {

    RoleEntity fromRoleDomainToRoleEntity(Role role);
    RoleResult fromRoleEntityToRoleResult(RoleEntity roleEntity);
}
