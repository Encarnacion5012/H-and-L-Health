package com.authservice.aplication.port.in;

import com.authservice.aplication.dto.commands.LoginCommand;
import com.authservice.aplication.dto.results.TokenResult;


public interface LoginUseCase {
    TokenResult login(LoginCommand loginCommand);
}
