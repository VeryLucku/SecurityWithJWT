package com.edu.security.Security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class BearerTokenServerAuthenticationConverter implements AuthenticationConverter {

    private final AppJwtHandler jwtHandler;
    private static final String BEARER_PREFIX = "Bearer ";
    private static final Function<String, String> getBearerValue = authValue -> authValue.substring(BEARER_PREFIX.length());

    @Override
    public Authentication convert(HttpServletRequest request) {
        return extractHeader(request)
                .map(getBearerValue)
                .map(jwtHandler::check)
                .map(UserAuthenticationBearer::create)
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException(""));
    }

    private Optional<String> extractHeader(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Authorization"));
    }
}
