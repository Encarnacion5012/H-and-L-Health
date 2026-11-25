package com.authservice.aplication.service;

import com.authservice.aplication.dto.commands.CreateDoctorCommand;
import com.authservice.aplication.dto.commands.CreateGenralUserWithRoleCommand;
import com.authservice.aplication.dto.commands.CreatePatientCommand;
import com.authservice.aplication.dto.results.UserResult;
import com.authservice.aplication.mapper.UserAplicationMapper;
import com.authservice.aplication.port.in.*;
import com.authservice.aplication.port.out.EncryptPasswordPort;
import com.authservice.aplication.port.out.persistense.*;
import com.authservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements
        ActivateUserUseCase,
        CreateDoctorUserUseCase,
        CreateGeneralUserUseCase,
        CreatePatientUserUseCase,
        DesactivateUserUseCase,
        FindUserByIdUseCase

{

    private final UserAplicationMapper userAplicationMapper;
    private final EncryptPasswordPort encryptPasswordPort;
    private final ActivateUserPort activateUserPort;
    private final CreateDoctorPort createDoctorPort;
    private final CreatePatientPort createPatientPort;
    private final CreateUserGeneralPort createUserGeneralPort;
    private final DesactivateUserPort desactivateUserPort;
    private final FindUserByIdPort findUserByIdPort;

    @Override
    public void executeActivateUser(UUID userId) {
        activateUserPort.activate(userId);
    }

    @Override
    public void executeCreateDoctor(CreateDoctorCommand createDoctorCommand) {
        String encryptedPassword = encryptPasswordPort.encryptPassword(createDoctorCommand.generalUserData().password());
        User user = userAplicationMapper.createUserFromCreateDoctorCommand(createDoctorCommand, encryptedPassword);
        createDoctorPort.createDoctor(user);
    }

    @Override
    public void executeCreateGeneralUser(CreateGenralUserWithRoleCommand createGenralUserWithRoleCommand) {
        String encryptedPassword = encryptPasswordPort.encryptPassword(createGenralUserWithRoleCommand.password());
        User user = userAplicationMapper.createUserFromCreateGeneralUserCommandWithRole(createGenralUserWithRoleCommand, encryptedPassword);
        createUserGeneralPort.createUser(user);
    }

    @Override
    public void executeCreatePatient(CreatePatientCommand createPatientCommand) {
        String encryptedPassword = encryptPasswordPort.encryptPassword(createPatientCommand.generalUserData().password());
        User user = userAplicationMapper.createUserFromCreatePatientCommand(createPatientCommand, encryptedPassword);
        createPatientPort.createPatient(user);
    }

    @Override
    public void executeDesactivateUser(UUID userId) {
        desactivateUserPort.desactivate(userId);
    }

    @Override
    public UserResult executeFindUserById(UUID id_user) {
        return findUserByIdPort.findById(id_user);
    }
}
