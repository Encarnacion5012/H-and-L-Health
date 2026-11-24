package com.authservice.aplication.dto.commands;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record CreateGenralUserWithRoleCommand(
        @NotBlank(message = "Username cannot be blank")
        String username,

        @NotBlank(message = "email cannot be blank")
        @Email(message = "Email has to be valid")
        String email,


        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 72, message = "Password must be between 8 and 72 characters")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character"
        )

        Set<Integer> idsRoles,
        String password,
        String profile_photo
) {
}
