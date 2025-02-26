package me.dhiren9939.jwtauth.dto.auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginUserDto {

    @NotBlank(message = "email cannot be empty")
    @Email(message = "invalid email format")
    private String email;

    @NotBlank(message = "password cannot be empty")
    @Size(min = 8,max = 32,message = "password must be of length 8 to 32")
    private String password;

    public LoginUserDto() {
    }

    public LoginUserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
