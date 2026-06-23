package com.vai.unified.auth.controller;

import com.vai.unified.auth.dto.AuthRequest;
import com.vai.unified.auth.dto.AuthResponse;
import com.vai.unified.auth.entity.User;
import com.vai.unified.auth.repository.UserRepository;
import com.vai.unified.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        log.info("LOGIN ATTEMPT - Username: {}, Password provided: {}", request.getUserId(), (request.getPassword() != null ? "YES" : "NO"));

        Optional<User> userOpt = userRepository.findByUsername(request.getUserId());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            log.info("USER FOUND - DB Hash: {}, Input Password: {}", user.getPasswordHash(), request.getPassword());
            
            if (user.getPasswordHash().equals(request.getPassword())) {
                String role = (user.getIsSuperAdmin() != null && user.getIsSuperAdmin() == 1) ? "ADMIN" : "USER";
                String token = jwtService.generateToken(user.getUsername(), role);

                return ResponseEntity.ok(AuthResponse.builder()
                        .token(token)
                        .userId(user.getUsername())
                        .userName(user.getFirstName() + " " + user.getLastName())
                        .roles(Collections.singletonList(role))
                        .success(true)
                        .message("Login successful")
                        .build());
            } else {
                log.warn("LOGIN FAILED - Password mismatch for user: {}", request.getUserId());
            }
        } else {
            log.warn("LOGIN FAILED - User not found: {}", request.getUserId());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(AuthResponse.builder()
                        .success(false)
                        .message("Invalid credentials")
                        .build());
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestParam String token, @RequestParam String userId) {
        return ResponseEntity.ok(jwtService.validateToken(token, userId));
    }
}
