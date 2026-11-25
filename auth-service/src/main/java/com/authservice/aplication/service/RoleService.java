package com.authservice.aplication.service;

import com.authservice.aplication.dto.commands.CreateRoleCommand;
import com.authservice.aplication.dto.results.RoleResult;
import com.authservice.aplication.mapper.RoleAplicationMapper;
import com.authservice.aplication.port.in.CreateRoleUseCase;
import com.authservice.aplication.port.in.FindRoleByIdUseCase;
import com.authservice.aplication.port.out.persistense.CreateRolePort;
import com.authservice.aplication.port.out.persistense.FindRoleByIdPort;
import com.authservice.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService implements
        CreateRoleUseCase,
        FindRoleByIdUseCase
{

    private final CreateRolePort createRolePort;
    private final FindRoleByIdPort findRoleByIdPort;
    private final RoleAplicationMapper roleAplicationMapper;

    @Override
    public void executeCreateRole(CreateRoleCommand createRoleCommand) {
        Role role = roleAplicationMapper.createRoleFromCreateRoleCommand(createRoleCommand);
        createRolePort.create(role);
    }

    @Override
    public RoleResult executeFindRoleById(Integer roleId) {

        return findRoleByIdPort.findById(roleId);
    }
}
