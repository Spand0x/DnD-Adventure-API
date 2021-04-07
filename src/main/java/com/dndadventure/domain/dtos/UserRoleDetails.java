package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.constants.UserRoleEnum;

public class UserRoleDetails {
    private String uuid;
    private UserRoleEnum role;

    public UserRoleDetails() {
    }

    public String getUuid() {
        return uuid;
    }

    public UserRoleDetails setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleDetails setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
