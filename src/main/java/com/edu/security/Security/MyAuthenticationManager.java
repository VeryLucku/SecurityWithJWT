package com.edu.security.Security;

import com.edu.security.Models.Entities.UserEntity;
import com.edu.security.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyAuthenticationManager implements AuthenticationManager {

    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();

        UserEntity user =  userService.findById(principal.id());

        if (!user.isEnabled()) {
            throw new DisabledException("User disabled");
        }


        return authentication;

    }
}


