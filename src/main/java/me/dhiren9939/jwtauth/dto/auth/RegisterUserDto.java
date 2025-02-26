package me.dhiren9939.jwtauth.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterUserDto extends LoginUserDto{

    @NotBlank(message = "username cannot be empty")
    @Size(min = 3,max = 32,message = "username must be of size 3 to 32")
    private String username;

    public RegisterUserDto() {
    }

    public RegisterUserDto(String username, String email, String password) {
        super(email,password);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
