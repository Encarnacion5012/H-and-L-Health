package com.authservice.aplication.config;

import com.authservice.aplication.port.out.persistense.ExistByEmailPort;
import com.authservice.aplication.port.out.persistense.ExistByUsernamePort;
import com.authservice.aplication.validator.fieldExist.ValidateEmailExist;
import com.authservice.aplication.validator.fieldExist.ValidateUsernameExist;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfiguration {
    @Bean
    public ValidateEmailExist validateEmailExist(ExistByEmailPort existByEmailPort) {
        return new ValidateEmailExist(existByEmailPort);
    }

    @Bean
    public ValidateUsernameExist validateUsernameExist(ExistByUsernamePort existByUsernamePort) {
        return new ValidateUsernameExist(existByUsernamePort);
    }
}
