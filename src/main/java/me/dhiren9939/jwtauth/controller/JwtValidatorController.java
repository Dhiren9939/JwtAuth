package me.dhiren9939.jwtauth.controller;

import jakarta.validation.Valid;
import me.dhiren9939.jwtauth.dto.JwtValidationDto;
import me.dhiren9939.jwtauth.service.auth.JwtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JwtValidatorController {

    private final JwtService jwtService;

    public JwtValidatorController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/api/auth/verify-jwt")
    public Map<Object,Object> verifyJwts(@Valid @RequestBody JwtValidationDto jwtDto) {
        return Map.of(
                "tokenStatus", jwtService.isTokenValid(jwtDto.getToken()) ? "valid" : "invalid"
        );
    }
}
