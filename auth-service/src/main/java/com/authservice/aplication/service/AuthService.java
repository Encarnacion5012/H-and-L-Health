package com.authservice.aplication.service;

import com.authservice.aplication.dto.commands.LoginCommand;
import com.authservice.aplication.dto.results.AuthenticatedUserResult;
import com.authservice.aplication.dto.results.TokenResult;
import com.authservice.aplication.port.in.LoginUseCase;
import com.authservice.aplication.port.out.security.AuthenticationPort;
import com.authservice.aplication.port.out.security.TokenGenerationPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements LoginUseCase {

    private final AuthenticationPort authenticationPort;
    private final TokenGenerationPort tokenGenerationPort;


    @Override
    public TokenResult login(LoginCommand loginCommand) {

        AuthenticatedUserResult authenticatedUserResult = authenticationPort.authenticate(loginCommand.email(), loginCommand.password());



        String tokenJWT =  tokenGenerationPort.generateToken(authenticatedUserResult);

        return new TokenResult(tokenJWT);
    }
}
