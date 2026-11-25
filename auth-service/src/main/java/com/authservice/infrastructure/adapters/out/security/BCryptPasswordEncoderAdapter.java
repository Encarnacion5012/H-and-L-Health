package com.authservice.infrastructure.adapters.out.security;

import com.authservice.aplication.port.out.EncryptPasswordPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BCryptPasswordEncoderAdapter implements EncryptPasswordPort {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String encryptPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
