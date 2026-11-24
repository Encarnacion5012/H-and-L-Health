package com.authservice.aplication.dto.commands;

public record LoginCommand(
        String email,
        String password
) {
}
