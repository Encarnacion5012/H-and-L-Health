package com.authservice.aplication.port.out.security;

import com.authservice.aplication.dto.results.AuthenticatedUserResult;

public interface TokenGenerationPort {
    String  generateToken(AuthenticatedUserResult authenticatedUserResult);
}
