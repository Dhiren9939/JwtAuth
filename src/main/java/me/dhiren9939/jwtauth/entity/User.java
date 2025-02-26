package me.dhiren9939.jwtauth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// user is keyword in dbs
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue
    long id;

    private String username;

    private String email;

    private String passwordHash;

    public User() {
    }

    public User(long id, String username, String email, String passwordHash) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
