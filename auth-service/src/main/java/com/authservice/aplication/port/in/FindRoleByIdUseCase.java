package com.authservice.aplication.port.in;

import com.authservice.aplication.dto.results.RoleResult;

public interface FindRoleByIdUseCase {
    RoleResult executeFindRoleById(Integer roleId);
}
