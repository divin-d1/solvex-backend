package com.solvex.dto;
import lombok.Getter;
import lombok.Setter;
import com.solvex.entity.User.Role;

@Getter @Setter
public class RegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private Role role;
}
