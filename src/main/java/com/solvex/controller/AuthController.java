package com.solvex.controller;
import com.solvex.dto.LoginRequest;
import com.solvex.dto.RegisterRequest;
import com.solvex.entity.User;
import com.solvex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
//@RequiredArgsConstructor

public class AuthController {
    @Autowired
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        User user = userService.register(
                request.getFullName(),
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request,HttpSession session){
        User user = userService.login(
                request.getEmail(),
                request.getPassword()
        );

        session.setAttribute("SOLVEX_SESSION", user.getId());
        return ResponseEntity.ok("Login Successfully");
    }
}
