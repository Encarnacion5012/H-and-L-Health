package com.authservice.aplication.dto.results;

import java.util.List;
import java.util.UUID;

public record AuthenticatedUserResult(
        UUID authUserId,
        String username,

        String email,

        List<String> roles
) {
}
