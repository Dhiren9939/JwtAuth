package me.dhiren9939.jwtauth.dto;

import jakarta.validation.constraints.NotBlank;

public class JwtValidationDto {

    @NotBlank(message = "Token cannot be empty.")
    private String token;

    public JwtValidationDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
