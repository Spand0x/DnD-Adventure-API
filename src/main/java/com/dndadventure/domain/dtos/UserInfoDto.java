package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.Campaign;
import com.dndadventure.domain.entities.Character;
import com.dndadventure.domain.entities.UserRole;

import java.util.Set;

public class UserInfoDto {
    private String username;
    private String email;
    private Set<UserRole> userRoles;
    private Set<Campaign> campaigns;
    private Set<Character> characters;

    public UserInfoDto() {
    }

    public String getUsername() {
        return username;
    }

    public UserInfoDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInfoDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public UserInfoDto setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    public Set<Campaign> getCampaigns() {
        return campaigns;
    }

    public UserInfoDto setCampaigns(Set<Campaign> campaigns) {
        this.campaigns = campaigns;
        return this;
    }

    public Set<Character> getCharacters() {
        return characters;
    }

    public UserInfoDto setCharacters(Set<Character> characters) {
        this.characters = characters;
        return this;
    }
}
