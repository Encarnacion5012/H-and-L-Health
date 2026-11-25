package com.authservice.aplication.port.in;

import com.authservice.aplication.dto.results.UserResult;

import java.util.UUID;

public interface FindUserByIdUseCase {
    UserResult executeFindUserById(UUID id_user);
}
