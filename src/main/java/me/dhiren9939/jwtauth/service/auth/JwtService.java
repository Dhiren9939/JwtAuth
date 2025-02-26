package me.dhiren9939.jwtauth.service.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import me.dhiren9939.jwtauth.entity.User;
import me.dhiren9939.jwtauth.exceptions.InvalidJwtException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    //    private static final Key SECRET_KEY = Jwts.SIG.HS256.key().build();
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("kinc(S(#H*@GDAd4436fr434#$3V54#4".getBytes(StandardCharsets.UTF_8));
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // day
    private static final JwtParser jwtParser = Jwts.parser().verifyWith(SECRET_KEY).build();

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

    public boolean isTokenValid(String token) {
        try {
            Claims claims = getAllClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (JwtException e) {
            throw new InvalidJwtException("invalid auth token");
        }
    }

    private Claims getAllClaims(String token) {
        return jwtParser.parseSignedClaims(token).getPayload();
    }
}
