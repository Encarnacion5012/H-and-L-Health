package com.authservice.aplication.validator.fieldExist;

import com.authservice.aplication.dto.commands.CreateGeneralUserWithNoRoleCommand;

public interface ValidateFieldsExistence {
    void validateFieldsExistence(String email, String username);
}
