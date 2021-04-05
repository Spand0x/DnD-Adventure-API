package com.dndadventure.domain.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserRegisterDto {
    private String email;
    private String username;
    private String password;
    private String repPassword;

    public UserRegisterDto() {
    }

    @Email(message = "Email is not valid")
    public String getEmail() {
        return email;
    }

    public UserRegisterDto setEmail(String email) {
        this.email = email;
        return this;
    }

    @NotBlank(message = "Username cannot be empty.")
    public String getUsername() {
        return username;
    }

    public UserRegisterDto setUsername(String username) {
        this.username = username;
        return this;
    }

    @NotBlank(message = "Password cannot be empty.")
    public String getPassword() {
        return password;
    }

    public UserRegisterDto setPassword(String password) {
        this.password = password;
        return this;
    }

    @NotBlank(message = "Repeat Password cannot be empty.")
    public String getRepPassword() {
        return repPassword;
    }

    public UserRegisterDto setRepPassword(String repPassword) {
        this.repPassword = repPassword;
        return this;
    }
}
