package com.edu.security.Security;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.UUID;

public class UserAuthenticationBearer {

    public static Authentication create(AppJwtHandler.VerificationResult verificationResult) {
        Claims claims = verificationResult.getClaims();
        String subject = claims.getSubject();

        String role = claims.get("role", String.class);
        String username = claims.get("username", String.class);

        var authorities = List.of(new SimpleGrantedAuthority(role));

        UUID principalId = UUID.fromString(subject);
        CustomPrincipal principal = new CustomPrincipal(principalId, username);

        return new UsernamePasswordAuthenticationToken(principal, null, authorities);
    }
}
