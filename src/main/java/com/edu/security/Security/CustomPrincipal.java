package com.edu.security.Security;

import lombok.*;

import java.util.UUID;

public record CustomPrincipal(UUID id, String username) {
}
