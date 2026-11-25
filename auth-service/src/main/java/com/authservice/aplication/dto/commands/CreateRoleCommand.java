package com.authservice.aplication.dto.commands;

public record CreateRoleCommand(
        String roleName,
        String description
) {

}
