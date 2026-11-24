package com.authservice.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Role {
    private Integer roleId;
    private String roleName;
    private String description;


    @Builder(toBuilder = true)
    private Role(Integer roleId, String roleName, String description) {
        this.roleId = roleId;
        this.roleName = verifyRoleName(roleName);
        this.description = description;
    }


    private String verifyRoleName(String roleName) {
        if (roleName.isBlank()) {
            throw new IllegalArgumentException("Role name cannot be blank");
        }
        return roleName;
    }
}
