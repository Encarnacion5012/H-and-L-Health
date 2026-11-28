package com.authservice.aplication.port.out.kafka;

import com.authservice.aplication.dto.commands.CreateDoctorCommand;

import java.time.Instant;
import java.util.UUID;

public interface SendDoctorRegisterEventPort {
     void sendDoctorRegisterEvent(UUID auth_user_id, Instant createdAT, CreateDoctorCommand createDoctorCommand);
}
