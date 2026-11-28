package com.authservice.infrastructure.adapters.out.security;
import com.authservice.aplication.dto.results.AuthenticatedUserResult;
import com.authservice.aplication.port.out.security.TokenGenerationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NimbusTokenAdapter implements TokenGenerationPort {
    private final JwtEncoder jwtEncoder;

    private  final String ISSUER = "K-and-L Health";

    @Override
    public String generateToken(AuthenticatedUserResult authenticatedUserResult) {

        List<String> roles = authenticatedUserResult.roles();


        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer(ISSUER)
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
                .subject(authenticatedUserResult.authUserId().toString())
                .claim("roles", roles)
                .claim("email", authenticatedUserResult.email())

                // Validación para evitar que explote si el nombre es null
                .claim("username", (authenticatedUserResult.username() != null) ? authenticatedUserResult.username() : "")

                .build();

        return  jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }
}
