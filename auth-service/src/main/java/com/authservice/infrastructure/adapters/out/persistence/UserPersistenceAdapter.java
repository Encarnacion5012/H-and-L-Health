package com.authservice.infrastructure.adapters.out.persistence;

import com.authservice.aplication.dto.results.UserResult;
import com.authservice.aplication.port.out.persistense.*;
import com.authservice.domain.model.Role_Name;
import com.authservice.domain.model.User;
import com.authservice.infrastructure.adapters.out.persistence.entity.RoleEntity;
import com.authservice.infrastructure.adapters.out.persistence.entity.UserEntity;
import com.authservice.infrastructure.adapters.out.persistence.mapper.UserPersistenceMapper;
import com.authservice.infrastructure.adapters.out.persistence.repository.RoleRepository;
import com.authservice.infrastructure.adapters.out.persistence.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements
        CreateUserGeneralPort,
        DesactivateUserPort,
        FindUserByIdPort,
        ActivateUserPort,
        CreateDoctorPort,
        CreatePatientPort,
        ExistByEmailPort,
        ExistByUsernamePort
{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    @Override
    public void createUser(User user) {
        UserEntity userEntity = userPersistenceMapper.fromUserDomainToUserEntity(user);

        Set<RoleEntity> roles = roleRepository.findAllByRoleIdIn(user.getIdsRoles());

        userEntity.relationshipHelperUserRoleEntity(roles);

        userRepository.save(userEntity);
    }

    @Override
    public void desactivate(UUID userId) {
        UserEntity userEntity = userRepository.findByAuthUserIdAndActiveTrue(userId)
                .orElseThrow(()-> new EntityNotFoundException("User not found with id: " + userId));

        userEntity.desactivateUser();

    }

    @Override
    public UserResult findById(UUID userId) {
        UserEntity userEntity = userRepository.findByAuthUserIdAndActiveTrue(userId)
                .orElseThrow(()-> new EntityNotFoundException("User not found with id: " + userId));


        return userPersistenceMapper.fromUserEntityToUserResult(userEntity);
    }

    @Override
    public void activate(UUID userId) {
        UserEntity userEntity = userRepository.findByAuthUserIdAndActiveTrue(userId)
                .orElseThrow(()-> new EntityNotFoundException("User not found with id: " + userId));

        userEntity.activateUser();
    }

    @Override
    public void createDoctor(User user) {
        UserEntity userEntity = userPersistenceMapper.fromUserDomainToUserEntity(user);
        RoleEntity roleEntity = roleRepository.findByRoleName(Role_Name.DOCTOR)
                .orElseThrow(()->  new EntityNotFoundException("Role not found with name: " + Role_Name.DOCTOR.name()));

        userEntity.relationshipHelperUserRoleEntity(Set.of(roleEntity));

        userRepository.save(userEntity);
    }

    @Override
    public void createPatient(User user) {
        UserEntity userEntity = userPersistenceMapper.fromUserDomainToUserEntity(user);
        RoleEntity roleEntity = roleRepository.findByRoleName(Role_Name.PATIENT)
                .orElseThrow(()->  new EntityNotFoundException("Role not found with name: " + Role_Name.PATIENT.name()));

        userEntity.relationshipHelperUserRoleEntity(Set.of(roleEntity));

        userRepository.save(userEntity);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
