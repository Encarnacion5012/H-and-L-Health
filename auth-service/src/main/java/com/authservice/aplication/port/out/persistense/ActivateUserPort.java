package com.authservice.aplication.port.out.persistense;

import java.util.UUID;

public interface ActivateUserPort {
    void activate(UUID userId);
}
