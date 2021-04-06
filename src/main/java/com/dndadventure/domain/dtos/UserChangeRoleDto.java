package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.constants.UserRoleEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class UserChangeRoleDto {
    private String uuid;
    private Set<UserRoleEnum> roles;
    private UserRoleEnum newRole;

    public UserChangeRoleDto() {
    }

    @NotBlank(message = "Uuid cannot be empty.")
    public String getUuid() {
        return uuid;
    }

    public UserChangeRoleDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public Set<UserRoleEnum> getRoles() {
        return roles;
    }

    public UserChangeRoleDto setRoles(Set<UserRoleEnum> roles) {
        this.roles = roles;
        return this;
    }

    @NotNull(message = "New role cannot be empty.")
    public UserRoleEnum getNewRole() {
        return newRole;
    }

    public UserChangeRoleDto setNewRole(UserRoleEnum newRole) {
        this.newRole = newRole;
        return this;
    }
}
