package com.authservice.aplication.mapper;

import com.authservice.aplication.dto.commands.CreatePatientCommand;
import com.authservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserAplicationMapper {

    @Mapping(target = "authUserId", ignore = true)
    @Mapping(target = "username", source = "createPatientCommand.generalUserData.username")
    @Mapping(target = "email", source = "createPatientCommand.generalUserData.email")
    @Mapping(target = "profilePhoto", source = "createPatientCommand.generalUserData.profile_photo")
    @Mapping(target = "password", source = "encryptedPassword")
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "desactivatedAt",  ignore = true)
    @Mapping(target = "activatedAt",  ignore = true)
    @Mapping(target = "idsRoles",  source = "idsRoles")
    User createUserFromCreatePatientCommand(CreatePatientCommand createPatientCommand, String encryptedPassword, Set<Integer> idsRoles);
}
