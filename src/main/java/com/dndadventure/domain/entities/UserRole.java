package com.dndadventure.domain.entities;

import com.dndadventure.domain.entities.constants.UserRoleEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity{
    private UserRoleEnum role;

    public UserRole() {
    }

    @Enumerated(value = EnumType.STRING)
    public UserRoleEnum getRole() {
        return role;
    }

    public UserRole setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}