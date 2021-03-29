package com.dndadventure.domain.dtos;

public class UserRegisterDto {
    private String email;
    private String username;
    private String password;
    private String repPassword;

    public UserRegisterDto() {
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRepPassword() {
        return repPassword;
    }

    public UserRegisterDto setRepPassword(String repPassword) {
        this.repPassword = repPassword;
        return this;
    }
}
