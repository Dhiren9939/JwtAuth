package me.dhiren9939.jwtauth.controller.auth;

import jakarta.validation.Valid;
import me.dhiren9939.jwtauth.dto.auth.LoginUserDto;
import me.dhiren9939.jwtauth.dto.auth.RegisterUserDto;
import me.dhiren9939.jwtauth.service.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<Map<String, Object>> loginUser(@Valid @RequestBody LoginUserDto loginUserDto) {
        String token = service.authenticateUser(loginUserDto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("token", token));
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<Map<String, Object>> registerUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
        long userId = service.registerUser(registerUserDto).getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("userId", userId));
    }
}
    