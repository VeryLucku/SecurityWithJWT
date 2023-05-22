package com.edu.security.Security;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
public record CustomPrincipal(UUID id, String username) {
}
