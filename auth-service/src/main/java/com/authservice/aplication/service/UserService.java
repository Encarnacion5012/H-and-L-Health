package com.authservice.aplication.service;

import com.authservice.aplication.dto.commands.CreateDoctorCommand;
import com.authservice.aplication.dto.commands.CreateGenralUserWithRoleCommand;
import com.authservice.aplication.dto.commands.CreatePatientCommand;
import com.authservice.aplication.dto.results.UserResult;
import com.authservice.aplication.mapper.UserAplicationMapper;
import com.authservice.aplication.port.in.*;
import com.authservice.aplication.port.out.kafka.SendDoctorRegisterEventPort;
import com.authservice.aplication.port.out.kafka.SendPatientRegisteredEventPort;
import com.authservice.aplication.port.out.kafka.SendUserRegisteredEventPort;
import com.authservice.aplication.port.out.security.EncryptPasswordPort;
import com.authservice.aplication.port.out.persistense.*;
import com.authservice.aplication.validator.fieldExist.ValidateFieldsExistence;
import com.authservice.domain.exception.DuplicatedResourceException;
import com.authservice.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
    private final SendPatientRegisteredEventPort sendPatientRegisteredEventPort;
    private final SendDoctorRegisterEventPort sendDoctorRegisterEventPort;
    private final SendUserRegisteredEventPort sendUserRegisteredEventPort;
    private final List<ValidateFieldsExistence> validateFieldsExistences;

    @Override
    public void executeActivateUser(UUID userId) {
        activateUserPort.activate(userId);
    }

    @Override
    @Transactional
    public void executeCreateDoctor(CreateDoctorCommand createDoctorCommand) {

        String encryptedPassword = encryptPasswordPort.encryptPassword(createDoctorCommand.generalUserData().password());

        User user = userAplicationMapper.createUserFromCreateDoctorCommand(createDoctorCommand, encryptedPassword);

        validateFieldsExistences.forEach(v-> v.validateFieldsExistence(
                createDoctorCommand.generalUserData().email(),
                createDoctorCommand.generalUserData().username()
        ));



        //REcuerda cooregir los violate constraint exeption

        sendDoctorRegisterEventPort.sendDoctorRegisterEvent(user.getAuthUserId(),user.getCreatedAt(),  createDoctorCommand);

        createDoctorPort.createDoctor(user);
    }

    @Override
    @Transactional
    public void executeCreateGeneralUser(CreateGenralUserWithRoleCommand createGenralUserWithRoleCommand) {
        String encryptedPassword = encryptPasswordPort.encryptPassword(createGenralUserWithRoleCommand.password());
        User user = userAplicationMapper.createUserFromCreateGeneralUserCommandWithRole(createGenralUserWithRoleCommand, encryptedPassword);

        validateFieldsExistences.forEach(v-> v.validateFieldsExistence(
                createGenralUserWithRoleCommand.email(),
                createGenralUserWithRoleCommand.username()
        ));



        sendUserRegisteredEventPort.sendUserRegisteredEvent(UUID.randomUUID(), user.getCreatedAt(), createGenralUserWithRoleCommand);
        createUserGeneralPort.createUser(user);
    }
    @Transactional
    @Override
    public void executeCreatePatient(CreatePatientCommand createPatientCommand) {
        String encryptedPassword = encryptPasswordPort.encryptPassword(createPatientCommand.generalUserData().password());
        User user = userAplicationMapper.createUserFromCreatePatientCommand(createPatientCommand, encryptedPassword);

        validateFieldsExistences.forEach(v-> v.validateFieldsExistence(
                createPatientCommand.generalUserData().email(),
                createPatientCommand.generalUserData().username()
        ));


        createPatientPort.createPatient(user);

        sendPatientRegisteredEventPort.sendPatientRegisteredEvent(user.getAuthUserId(),user.getCreatedAt(), createPatientCommand);
    }


    @Override
    @Transactional
    public void executeDesactivateUser(UUID userId) {
        desactivateUserPort.desactivate(userId);
    }

    @Override
    @Transactional
    public UserResult executeFindUserById(UUID id_user) {
        return findUserByIdPort.findById(id_user);
    }
}
