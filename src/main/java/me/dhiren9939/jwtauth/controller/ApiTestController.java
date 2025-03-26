package me.dhiren9939.jwtauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApiTestController {

    @GetMapping("/api/test-jwt-f")
    public Map<Object, Object> protectedEndpoint() {
        return Map.of("message","Request has reached the controller through the filter chain.");
    }
}
