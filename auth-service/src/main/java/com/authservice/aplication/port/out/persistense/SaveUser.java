package com.authservice.aplication.port.out.persistense;

import com.authservice.domain.model.User;

public interface SaveUser {
    void save(User user);
}
