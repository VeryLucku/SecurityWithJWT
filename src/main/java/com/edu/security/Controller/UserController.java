package com.edu.security.Controller;

import com.edu.security.Mapper.UserMapper;
import com.edu.security.Models.DTO.UserDTO;
import com.edu.security.Models.Entities.UserEntity;
import com.edu.security.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public UserDTO registerUser(@Valid @RequestBody UserDTO userDTO) {
        UserEntity user = userService.registerUser(userMapper.map(userDTO));
        log.info("User with username {} was successfully created, id {}", user.getUsername(), user.getId());

        return userMapper.map(
                user
                );
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/info")
    public UserDTO getUserInfo(@RequestParam UUID userId) {
        return userMapper.map(
                userService.findById(userId)
        );
    }

}
