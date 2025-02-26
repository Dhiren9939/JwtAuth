package me.dhiren9939.jwtauth.exceptions;

public class InvalidJwtException extends RuntimeException {

    public InvalidJwtException(String message) {
        super(message);
    }
}
