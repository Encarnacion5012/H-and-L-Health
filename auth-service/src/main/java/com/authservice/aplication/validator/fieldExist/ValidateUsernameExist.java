package com.authservice.aplication.validator.fieldExist;

import com.authservice.aplication.dto.commands.CreateGeneralUserWithNoRoleCommand;
import com.authservice.aplication.port.out.persistense.ExistByUsernamePort;
import com.authservice.domain.exception.DuplicatedResourceException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidateUsernameExist implements ValidateFieldsExistence {
    private final ExistByUsernamePort existByUsernamePort;

    @Override
    public void validateFieldsExistence(String email, String username) {
        if (existByUsernamePort.existByUsername(username)){
            throw new DuplicatedResourceException("username", "Username already exists");
        }

    }
}
