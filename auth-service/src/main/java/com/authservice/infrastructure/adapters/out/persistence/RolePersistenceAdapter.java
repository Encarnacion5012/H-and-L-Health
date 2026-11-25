package com.authservice.infrastructure.adapters.out.persistence;

import com.authservice.aplication.dto.results.RoleResult;
import com.authservice.aplication.port.out.persistense.CreateRolePort;
import com.authservice.aplication.port.out.persistense.FindRoleByIdPort;
import com.authservice.domain.model.Role;
import com.authservice.infrastructure.adapters.out.persistence.entity.RoleEntity;
import com.authservice.infrastructure.adapters.out.persistence.mapper.RolePersistenceMapper;
import com.authservice.infrastructure.adapters.out.persistence.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RolePersistenceAdapter implements FindRoleByIdPort, CreateRolePort {
    private final RoleRepository roleRepository;
    private final RolePersistenceMapper rolePersistenceMapper;

    @Override
    public void create(Role role) {
        RoleEntity roleEntity = rolePersistenceMapper.fromRoleDomainToRoleEntity(role);
        roleRepository.save(roleEntity);
    }

    @Override
    public RoleResult findById(Integer id) {
      RoleEntity roleEntity =  roleRepository.findById(id)
              .orElseThrow(()-> new EntityNotFoundException("Role not found"));

        return rolePersistenceMapper.fromRoleEntityToRoleResult(roleEntity);
    }
}
