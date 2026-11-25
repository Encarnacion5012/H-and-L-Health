package com.authservice.aplication.port.in;

import java.util.UUID;

public interface ActivateUserUseCase {
    void executeActivateUser(UUID userId);
}
