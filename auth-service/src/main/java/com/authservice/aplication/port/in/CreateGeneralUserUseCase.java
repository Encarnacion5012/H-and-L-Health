package com.authservice.aplication.port.in;

import com.authservice.aplication.dto.commands.CreateGenralUserWithRoleCommand;

public interface CreateGeneralUserUseCase {
    void executeCreateGeneralUser(CreateGenralUserWithRoleCommand createGenralUserWithRoleCommand);
}
