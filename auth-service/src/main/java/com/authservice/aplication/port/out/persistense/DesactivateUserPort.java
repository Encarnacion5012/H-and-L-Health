package com.authservice.aplication.port.out.persistense;


import java.util.UUID;

public interface DesactivateUserPort {
    void desactivate(UUID userId);
}
