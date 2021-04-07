package com.dndadventure.domain.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserChangeRoleDto {
    private String uuid;
    private String newRole;

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

    @NotNull(message = "New role cannot be empty.")
    public String getNewRole() {
        return newRole;
    }

    public UserChangeRoleDto setNewRole(String newRole) {
        this.newRole = newRole;
        return this;
    }
}
