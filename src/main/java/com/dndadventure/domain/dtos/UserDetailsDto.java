package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.constants.UserRoleEnum;

import java.util.Set;

public class UserDetailsDto {
    private String uuid;
    private String username;
    private String email;
    private Set<UserRoleEnum> roles;

    public UserDetailsDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public UserDetailsDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDetailsDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDetailsDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<UserRoleEnum> getRoles() {
        return roles;
    }

    public UserDetailsDto setRoles(Set<UserRoleEnum> roles) {
        this.roles = roles;
        return this;
    }
}
