package com.authservice.aplication.port.in;

import com.authservice.aplication.dto.commands.CreatePatientCommand;

public interface CreatePatientUserUseCase {
    void execute (CreatePatientCommand createPatientCommand);
}
