package com.authservice.infrastructure.adapters.in.rest;

import com.authservice.aplication.dto.commands.LoginCommand;
import com.authservice.aplication.dto.results.TokenResult;
import com.authservice.aplication.port.in.LoginUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final LoginUseCase loginUseCase;


    @PostMapping("/login")
    public ResponseEntity<TokenResult> login(@Valid @RequestBody LoginCommand loginCommand) {


        TokenResult token = loginUseCase.login(loginCommand);
        return ResponseEntity.ok(token);
    }

}
