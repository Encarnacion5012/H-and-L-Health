package com.authservice.aplication.port.out.persistense;


import java.util.UUID;

public interface DesactivateUser {
    void desactivate(UUID userId);
}
