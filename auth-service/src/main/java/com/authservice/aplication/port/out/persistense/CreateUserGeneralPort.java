package com.authservice.aplication.port.out.persistense;

import com.authservice.domain.model.User;

public interface CreateUserGeneralPort {
    void createUser(User user);
}
