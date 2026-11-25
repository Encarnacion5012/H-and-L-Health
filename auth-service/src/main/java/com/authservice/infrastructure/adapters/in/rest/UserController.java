package com.authservice.infrastructure.adapters.in.rest;

import com.authservice.aplication.dto.commands.CreateDoctorCommand;
import com.authservice.aplication.dto.commands.CreateGenralUserWithRoleCommand;
import com.authservice.aplication.dto.commands.CreatePatientCommand;
import com.authservice.aplication.dto.results.UserResult;
import com.authservice.aplication.port.in.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final ActivateUserUseCase activateUserUseCase;
    private final CreateDoctorUserUseCase createDoctorUserUseCase;
    private final CreateGeneralUserUseCase createGeneralUserUseCase;
    private final CreatePatientUserUseCase createPatientUserUseCase;
    private final DesactivateUserUseCase desactivateUserUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;



    @PostMapping("/{id_user}/activate")
    public ResponseEntity<Void> activateUser(@PathVariable UUID id_user) {
        activateUserUseCase.executeActivateUser(id_user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create-doctor")
    public ResponseEntity<Void> createDoctor(@Valid @RequestBody CreateDoctorCommand createDoctorCommand){
        createDoctorUserUseCase.executeCreateDoctor(createDoctorCommand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/create-general-user")
    public ResponseEntity<Void> createGeneralUser(@Valid @RequestBody CreateGenralUserWithRoleCommand createGeneralUserWithRoleCommand){
        createGeneralUserUseCase.executeCreateGeneralUser(createGeneralUserWithRoleCommand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/create-patient")
    public ResponseEntity<Void> createPatient(@Valid @RequestBody CreatePatientCommand createDoctorCommand){
        createPatientUserUseCase.executeCreatePatient(createDoctorCommand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{id_user}/desactivate")
    public ResponseEntity<Void> desactivateUser(@PathVariable UUID id_user) {
        desactivateUserUseCase.executeDesactivateUser(id_user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id_user}/getById")
    public ResponseEntity<UserResult> getUserById(@PathVariable UUID id_user) {
        UserResult userResult = findUserByIdUseCase.executeFindUserById(id_user);
        return ResponseEntity.ok().body(userResult);
    }
}
