package me.dhiren9939.jwtauth.service.auth;

import me.dhiren9939.jwtauth.dto.auth.LoginUserDto;
import me.dhiren9939.jwtauth.dto.auth.RegisterUserDto;
import me.dhiren9939.jwtauth.entity.User;
import me.dhiren9939.jwtauth.exceptions.InvalidCredentialsException;
import me.dhiren9939.jwtauth.exceptions.UserAlreadyExistsException;
import me.dhiren9939.jwtauth.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepo repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepo repo, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User registerUser(RegisterUserDto userDto) {
        if (repo.findByEmail(userDto.getEmail()).isPresent())
            throw new UserAlreadyExistsException("user already exists with this email");


        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userDto.getPassword()));
        return repo.save(user);
    }

    public String authenticateUser(LoginUserDto userDto) {
        Optional<User> userOptional = repo.findByEmail(userDto.getEmail());
        if (userOptional.isEmpty())
            throw new InvalidCredentialsException("invalid credentials");

        User user = userOptional.get();
        boolean passwordMatch = passwordEncoder.matches(userDto.getPassword(), user.getPasswordHash());
        if (!passwordMatch)
            throw new InvalidCredentialsException("invalid credentials");

        return jwtService.generateToken(user);
    }
}
