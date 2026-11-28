package com.authservice.infrastructure.adapters.out.persistence.mapper;

import com.authservice.aplication.dto.results.UserResult;
import com.authservice.domain.model.User;
import com.authservice.infrastructure.adapters.out.persistence.entity.RoleEntity;
import com.authservice.infrastructure.adapters.out.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {


    @Mapping(target = "roles", ignore = true)
    UserEntity fromUserDomainToUserEntity(User user);


    @Mapping(target = "profile_photo", source = "userEntity.profilePhoto")
    UserResult fromUserEntityToUserResult(UserEntity userEntity);



    default String roleToRolName(RoleEntity role) {
        if (role == null || role.getRoleName() == null) {
            return null;
        }
        return role.getRoleName().name();
    }



}
