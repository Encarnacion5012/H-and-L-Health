package com.authservice.aplication.port.in;

import java.util.UUID;

public interface DesactivateUserUseCase {
    void executeDesactivateUser(UUID userId);
}
