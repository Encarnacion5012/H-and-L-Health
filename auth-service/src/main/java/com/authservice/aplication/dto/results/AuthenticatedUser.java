package com.authservice.aplication.dto.results;

import java.util.List;

public record AuthenticatedUser(
        String username,

        String email,

        List<String> roles
) {
}
