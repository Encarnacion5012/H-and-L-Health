package com.authservice.aplication.mapper;

import com.authservice.aplication.dto.commands.CreateDoctorCommand;
import com.authservice.aplication.dto.commands.CreateGenralUserWithRoleCommand;
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
    @Mapping(target = "idsRoles",  ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User createUserFromCreatePatientCommand(CreatePatientCommand createPatientCommand, String encryptedPassword);

    @Mapping(target = "authUserId", ignore = true)
    @Mapping(target = "username", source = "createDoctorCommand.generalUserData.username")
    @Mapping(target = "email", source = "createDoctorCommand.generalUserData.email")
    @Mapping(target = "profilePhoto", source = "createDoctorCommand.generalUserData.profile_photo")
    @Mapping(target = "password", source = "encryptedPassword")
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "desactivatedAt",  ignore = true)
    @Mapping(target = "activatedAt",  ignore = true)
    @Mapping(target = "idsRoles",  ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User createUserFromCreateDoctorCommand(CreateDoctorCommand createDoctorCommand, String encryptedPassword);

    @Mapping(target = "authUserId", ignore = true)
    @Mapping(target = "username", source = "createGenralUserWithRoleCommand.username")
    @Mapping(target = "email", source = "createGenralUserWithRoleCommand.email")
    @Mapping(target = "profilePhoto", source = "createGenralUserWithRoleCommand.profile_photo")
    @Mapping(target = "password", source = "encryptedPassword")
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "desactivatedAt",  ignore = true)
    @Mapping(target = "activatedAt",  ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User createUserFromCreateGeneralUserCommandWithRole(CreateGenralUserWithRoleCommand createGenralUserWithRoleCommand, String encryptedPassword);
}
