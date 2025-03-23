package me.dhiren9939.jwtauth.controller.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProtectedController {

    @GetMapping("/api/protected")
    public String protectedEndpoint() {
        return "Hello World";
    }
}
