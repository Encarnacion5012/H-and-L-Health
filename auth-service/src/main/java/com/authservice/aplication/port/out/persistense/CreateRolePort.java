package com.authservice.aplication.port.out.persistense;


import com.authservice.domain.model.Role;

public interface CreateRolePort {
    void create(Role role);
}
