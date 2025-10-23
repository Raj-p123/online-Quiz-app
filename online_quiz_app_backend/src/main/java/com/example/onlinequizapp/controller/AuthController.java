package com.example.onlinequizapp.controller;

import com.example.onlinequizapp.dto.LoginRequest;
import com.example.onlinequizapp.dto.SignupRequest;
import com.example.onlinequizapp.model.User;
import com.example.onlinequizapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignupRequest req) {
        if (userRepository.existsByUsername(req.username())) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username already taken"));
        }
        if (userRepository.existsByEmail(req.email())) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email already in use"));
        }
        User u = new User();
        u.setUsername(req.username());
        u.setEmail(req.email());
        u.setPassword(passwordEncoder.encode(req.password()));
        u.setRole("ROLE_USER");
        userRepository.save(u);
        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        // NOTE: For brevity this sample only verifies username/password and returns a dummy token.
        var opt = userRepository.findByUsername(req.username());
        if (opt.isEmpty()) return ResponseEntity.status(401).body(Map.of("message","Invalid credentials"));
        User u = opt.get();
        if (!passwordEncoder.matches(req.password(), u.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("message","Invalid credentials"));
        }
        // Normally you'd return a signed JWT. Here we'll return a simple placeholder.
        return ResponseEntity.ok(Map.of("token","DUMMY-JWT-TOKEN-FOR-" + u.getUsername(), "role", u.getRole()));
    }
}
