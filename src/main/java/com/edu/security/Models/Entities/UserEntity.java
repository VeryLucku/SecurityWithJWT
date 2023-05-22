package com.edu.security.Models.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;

    private String password;

    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
