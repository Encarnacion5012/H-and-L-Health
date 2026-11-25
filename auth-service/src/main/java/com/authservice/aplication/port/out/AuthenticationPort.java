package com.authservice.aplication.port.out;

import com.authservice.aplication.dto.results.AuthenticatedUserResult;

public interface AuthenticationPort {
AuthenticatedUserResult authenticate(String username, String password);
}
