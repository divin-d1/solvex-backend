package com.solvex.dto;
import com.solvex.entity.User.Role;

import java.util.UUID;

public record UserResponse(
        UUID userId,
        String fullName,
        String email,
        Role role
) {}
