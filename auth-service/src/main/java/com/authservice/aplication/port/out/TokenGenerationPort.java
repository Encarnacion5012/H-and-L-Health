package com.authservice.aplication.port.out;

import com.authservice.aplication.dto.commands.TokenSubject;

public interface TokenGenerationPort {
    String  generateToken(TokenSubject tokenSubject);
}
