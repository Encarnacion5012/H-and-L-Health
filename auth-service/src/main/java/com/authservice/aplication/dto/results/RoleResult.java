package com.authservice.aplication.dto.results;

public record RoleResult(
        Integer roleId,
        String roleName,
        String description
) {
}
