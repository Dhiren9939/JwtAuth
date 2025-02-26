package me.dhiren9939.jwtauth.service.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import me.dhiren9939.jwtauth.entity.User;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    //    private static final Key SECRET_KEY = Jwts.SIG.HS256.key().build();
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor("kinc(S(#H*@GDAd4436fr434#$3V54#4".getBytes(StandardCharsets.UTF_8));
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // day


    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("username", user.getUsername())
                .claim("userId", user.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();

    }
}
