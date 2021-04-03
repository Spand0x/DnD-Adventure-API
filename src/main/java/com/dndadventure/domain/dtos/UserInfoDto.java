package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.constants.UserRoleEnum;

import java.util.Set;

public class UserInfoDto {
    private String username;
    private String email;
    private Set<UserRoleEnum> roles;
    //    private Set<Campaign> campaigns;
    private Set<CharacterBaseInfoDto> characters;

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

    public Set<UserRoleEnum> getRoles() {
        return roles;
    }

    public UserInfoDto setRoles(Set<UserRoleEnum> roles) {
        this.roles = roles;
        return this;
    }

    //    public Set<Campaign> getCampaigns() {
//        return campaigns;
//    }
//
//    public UserInfoDto setCampaigns(Set<Campaign> campaigns) {
//        this.campaigns = campaigns;
//        return this;
//    }

    public Set<CharacterBaseInfoDto> getCharacters() {
        return characters;
    }

    public UserInfoDto setCharacters(Set<CharacterBaseInfoDto> characters) {
        this.characters = characters;
        return this;
    }
}
