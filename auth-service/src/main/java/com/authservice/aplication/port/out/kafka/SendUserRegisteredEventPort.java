package com.authservice.aplication.port.out.kafka;



import com.authservice.aplication.dto.commands.CreateGenralUserWithRoleCommand;

import java.time.Instant;
import java.util.UUID;

public interface SendUserRegisteredEventPort {
    void sendUserRegisteredEvent(UUID auth_user_id, Instant created_At, CreateGenralUserWithRoleCommand command);
}
