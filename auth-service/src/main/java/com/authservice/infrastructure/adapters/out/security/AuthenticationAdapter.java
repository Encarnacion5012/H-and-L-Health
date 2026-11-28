package com.authservice.infrastructure.adapters.out.security;

import com.authservice.aplication.dto.results.AuthenticatedUserResult;
import com.authservice.aplication.port.out.security.AuthenticationPort;
import com.authservice.infrastructure.adapters.out.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationAdapter implements AuthenticationPort {
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticatedUserResult authenticate(String username, String password) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        UserEntity userEntity = (UserEntity) userDetails;

        return new AuthenticatedUserResult(
                userEntity.getAuthUserId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                roles
                );
    }
}
