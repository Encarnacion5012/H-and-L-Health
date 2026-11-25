package com.authservice.aplication.mapper;

import com.authservice.aplication.dto.commands.CreateRoleCommand;
import com.authservice.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleAplicationMapper {
    @Mapping(target = "roleId", ignore = true)
    Role createRoleFromCreateRoleCommand(CreateRoleCommand command);

}
