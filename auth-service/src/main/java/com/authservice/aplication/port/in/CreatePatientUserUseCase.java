package com.authservice.aplication.port.in;

import com.authservice.aplication.dto.commands.CreatePatientCommand;

public interface CreatePatientUserUseCase {
    void executeCreatePatient (CreatePatientCommand createPatientCommand);
}
