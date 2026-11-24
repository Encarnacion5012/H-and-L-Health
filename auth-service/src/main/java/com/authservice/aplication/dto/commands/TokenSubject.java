package com.authservice.aplication.dto.commands;

import java.util.List;
import java.util.UUID;

public record TokenSubject(
        UUID authUserId,
        String username,

        String email,

        List<String> roles
) {
}
