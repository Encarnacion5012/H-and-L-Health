package com.authservice.infrastructure.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class NimbusTokenConfig {

    @Value("${app.jwt.private-key:}")
    private String privateKeyPem;

    @Value("${app.jwt.public-key}")
    private String publicKeyPem;

    @Bean
    public JwtEncoder jwtEncoder() throws NoSuchAlgorithmException, InvalidKeySpecException {
        RSAPublicKey publicKey = loadPublicKey();
        RSAPrivateKey privateKey = loadPrivateKey();

        JWK jwk = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));

        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public JwtDecoder jwtDecoder() throws NoSuchAlgorithmException, InvalidKeySpecException {
        return NimbusJwtDecoder.withPublicKey(loadPublicKey()).build();
    }

    private RSAPrivateKey loadPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (privateKeyPem == null || privateKeyPem.isBlank()) {
            return null;
        }

        String cleanKey = privateKeyPem
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replace("\\n", "")
                .replaceAll("\\s+", "");

        byte[] decoded = Base64.getDecoder().decode(cleanKey);
        return (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(decoded));
    }

    private RSAPublicKey loadPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String cleanKey = publicKeyPem
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replace("\\n", "")
                .replaceAll("\\s+", "");

        byte[] decoded = Base64.getDecoder().decode(cleanKey);
        return (RSAPublicKey) KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(decoded));
    }
}