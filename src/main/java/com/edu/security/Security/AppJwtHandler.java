package com.edu.security.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class AppJwtHandler {

    private final String secret;


    public VerificationResult check(String accessToken) {
        return verify(accessToken);
    }

    private VerificationResult verify(String token) {
        Claims claims = getClaimsFromToken(token);
        final Date expirationDate = claims.getExpiration();

        if (expirationDate.before(new Date())) {
            throw new RuntimeException("Token expired");
        }

        return new VerificationResult(claims, token);
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(secret.getBytes()))
                .parseClaimsJws(token)
                .getBody();
    }

    @Data
    @AllArgsConstructor
    public static class VerificationResult {
        private Claims claims;
        private String token;
    }
}
