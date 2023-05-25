package com.edu.security.Controller;

import com.edu.security.Mapper.UserMapper;
import com.edu.security.Models.DTO.AuthRequestDto;
import com.edu.security.Models.DTO.AuthResponseDto;
import com.edu.security.Models.DTO.UserDTO;
import com.edu.security.Models.Entities.UserEntity;
import com.edu.security.Security.CustomPrincipal;
import com.edu.security.Security.SecurityService;
import com.edu.security.Security.TokenDetails;
import com.edu.security.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final SecurityService securityService;

    @PostMapping("/register")
    public UserDTO registerUser(@Valid @RequestBody UserDTO userDTO) {
        UserEntity user = userService.registerUser(userMapper.map(userDTO));
        log.info("User with username {} was successfully created, id {}", user.getUsername(), user.getId());

        return userMapper.map(
                user
                );
    }

    @PostMapping("/login")
    public AuthResponseDto login (@RequestBody AuthRequestDto dto) {
        TokenDetails tokenDetails = securityService.authenticate(dto.getUsername(), dto.getPassword());
        return AuthResponseDto.builder()
                .userId(tokenDetails.getUserId())
                .token(tokenDetails.getToken())
                .issuedAt(tokenDetails.getIssuesAt())
                .expiresAt(tokenDetails.getExpiresAt())
                .build();
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/info")
    public UserDTO getUserInfo(@AuthenticationPrincipal CustomPrincipal principal) {
        return userMapper.map(
                userService.findById(principal.id())
        );
    }


}
