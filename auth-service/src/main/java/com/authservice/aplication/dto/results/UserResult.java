package com.authservice.aplication.dto.results;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record UserResult(

        UUID authUserId,

        String username,


        String email,

        String profile_photo,

        Instant createdAt,

        List<String> roles
) {
}
