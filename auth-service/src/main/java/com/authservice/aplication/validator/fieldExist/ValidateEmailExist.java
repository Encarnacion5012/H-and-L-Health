package com.authservice.aplication.validator.fieldExist;

import com.authservice.aplication.dto.commands.CreateGeneralUserWithNoRoleCommand;
import com.authservice.aplication.port.out.persistense.ExistByEmailPort;
import com.authservice.domain.exception.DuplicatedResourceException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidateEmailExist implements ValidateFieldsExistence {
    private final ExistByEmailPort existByEmailPort;

    @Override
    public void validateFieldsExistence(String email, String username) {
        if (existByEmailPort.existByEmail(email)){
            throw new DuplicatedResourceException("email", "email already exists");
        }
    }
}
