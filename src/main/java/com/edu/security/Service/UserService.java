package com.edu.security.Service;

import com.edu.security.Models.Entities.UserEntity;
import com.edu.security.Models.Entities.UserRole;
import com.edu.security.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity registerUser(UserEntity user) {
        UserEntity user1 = user.toBuilder()
                .password(passwordEncoder.encode(user.getPassword()))
                .enabled(true)
                .role(UserRole.USER)
                .build();
        return userRepository.save(
                user1);
    }

    public UserEntity findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Specified user isn't found"));
    }

    public Optional<UserEntity> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
