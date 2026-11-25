package com.authservice.aplication.port.out.persistense;

import com.authservice.aplication.dto.results.RoleResult;

public interface FindRoleByIdPort {
RoleResult findById(Integer id);
}
