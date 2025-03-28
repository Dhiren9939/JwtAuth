package me.dhiren9939.jwtauth.controller;

import jakarta.validation.Valid;
import me.dhiren9939.jwtauth.dto.auth.LoginUserDto;
import me.dhiren9939.jwtauth.dto.auth.RegisterUserDto;
import me.dhiren9939.jwtauth.service.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/api/public/login")
    public ResponseEntity<Map<String, Object>> loginUser(@Valid @RequestBody LoginUserDto loginUserDto) {
        String token = service.authenticateUser(loginUserDto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("token", token));
    }

    @PostMapping("/api/public/user")
    public ResponseEntity<Map<String, Object>> registerUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
        long userId = service.registerUser(registerUserDto).getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("userId", userId));
    }

    @DeleteMapping("/api/user")
    public Map<String,Object> removeUser(@Valid @RequestBody LoginUserDto userDto){
        service.removeUser(userDto);
        return Map.of("message" ,"User successfully removed.");
    }
}
    