package com.authservice.aplication.port.out;

import com.authservice.aplication.dto.results.AuthenticatedUser;

public interface AuthenticationPort {
AuthenticatedUser authenticate(String username, String password);
}
