package com.solvex.controller;
import com.solvex.dto.LoginRequest;
import com.solvex.dto.RegisterRequest;
import com.solvex.dto.UserResponse;
import com.solvex.entity.User;
import com.solvex.service.RecaptchaService;
import com.solvex.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.solvex.entity.User.Role;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {
    private final UserService userService;
    private final  RecaptchaService recaptchaService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){

        Role roleToAssign = request.getRole();

        if(roleToAssign != Role.USER && roleToAssign != Role.INNOVATOR){
            return ResponseEntity
                    .status(401)
                    .body(Map.of(
                            "error","Invalid role"
                    ));
        }

        User user = userService.register(
                request.getFullName(),
                request.getEmail(),
                request.getPassword(),
                roleToAssign
        );
        return ResponseEntity
                .status(HttpStatusCode.valueOf(201))
                .body(Map.of(
                        "success","Registration successfully"
                ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession httpSession){
        if(!recaptchaService.validate(request.getRecaptchaToken())){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Invalid captcha"));
        }

        User user = userService.login(request.getEmail(), request.getPassword());
        httpSession.setAttribute("SOLVEX_SESSION",user.getId());
        return ResponseEntity
                .status(HttpStatusCode.valueOf(200))
                .body(Map.of(
                        "success", "Login successfully"
                ));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpSession session){
        UUID userId = (UUID) session.getAttribute("SOLVEX_SESSION");

        if (userId == null){
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(403))
                    .body(Map.of(
                            "error","Please login"
                    ));
        }
        User user = userService.getUser(userId);

        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole()
        );
        return ResponseEntity
                .status(HttpStatusCode.valueOf(200))
                .body(Map.of(
                        "success", "User returned successfully",
                        "user",userResponse
                ));
    }
}