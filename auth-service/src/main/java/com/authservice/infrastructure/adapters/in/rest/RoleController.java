package com.authservice.infrastructure.adapters.in.rest;


import com.authservice.aplication.dto.commands.CreateRoleCommand;
import com.authservice.aplication.dto.results.RoleResult;
import com.authservice.aplication.port.in.CreateRoleUseCase;
import com.authservice.aplication.port.in.FindRoleByIdUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final CreateRoleUseCase createRoleUseCase;
    private final FindRoleByIdUseCase findRoleByIdUseCase;

    @PostMapping("/create")
    public ResponseEntity<Void> createRole(@RequestBody @Valid CreateRoleCommand createRoleCommand) {
        createRoleUseCase.executeCreateRole(createRoleCommand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id_role}/getById")
    public ResponseEntity<RoleResult> getRoleById(@PathVariable Integer id_role) {
        RoleResult roleResult = findRoleByIdUseCase.executeFindRoleById(id_role);
        return ResponseEntity.ok(roleResult);
    }
}
