package com.solvex.service;
import com.solvex.Exception.AuthException;
import com.solvex.Exception.EmailAlreadyExistException;
import com.solvex.Exception.NotFoundException;
import com.solvex.entity.User;
import com.solvex.repository.UserRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.solvex.entity.User.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(String fullName, String email, String password, Role role){
        if(userRepository.existsByEmail(email)){
            throw new EmailAlreadyExistException("Email already exist");
        }
        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public User login(String email, String password){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new AuthException("Invalid email or password"));
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new AuthException("Invalid email or password");
        }
        return user;
    }

    public User getUser(UUID userId){
        return userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User not found"));
    }
}
