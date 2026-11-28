package com.authservice.aplication.port.out.kafka;

import com.authservice.aplication.dto.commands.CreatePatientCommand;

import java.time.Instant;
import java.util.UUID;

public interface SendPatientRegisteredEventPort {
    void sendPatientRegisteredEvent(UUID auth_user_id, Instant createdAT, CreatePatientCommand createPatientCommand);
}
