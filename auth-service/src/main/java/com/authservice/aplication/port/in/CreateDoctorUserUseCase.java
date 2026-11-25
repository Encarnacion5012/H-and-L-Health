package com.authservice.aplication.port.in;

import com.authservice.aplication.dto.commands.CreateDoctorCommand;

public interface CreateDoctorUserUseCase {
    void executeCreateDoctor (CreateDoctorCommand createDoctorCommandte);
}
