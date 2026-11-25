package com.authservice.aplication.port.in;

import com.authservice.aplication.dto.commands.CreateRoleCommand;

public interface CreateRoleUseCase {
    void executeCreateRole(CreateRoleCommand createRoleCommand);
}
