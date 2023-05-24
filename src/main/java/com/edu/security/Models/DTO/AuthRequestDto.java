package com.edu.security.Models.DTO;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String username;
    private String password;
}
