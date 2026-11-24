package com.authservice.aplication.port.in;

import java.util.UUID;

public interface DesactivateUserUseCase {
    void execute(UUID userId);
}
