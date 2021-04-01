package com.dndadventure.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dnd_users")
public class User extends BaseEntity{
    private String username;
    private String email;
    private String password;
    private Set<UserRole> userRoles;
    private Set<Campaign> campaigns;
    private Set<Character> characters;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "dnd_users_roles",
        joinColumns = @JoinColumn(name = "user_uuid", referencedColumnName = "uuid"),
        inverseJoinColumns = @JoinColumn(name = "role_uuid", referencedColumnName = "uuid"))
    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public User setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "dnd_users_campaigns",
        joinColumns = @JoinColumn(name = "user_uuid", referencedColumnName = "uuid"),
        inverseJoinColumns = @JoinColumn(name = "campaign_uuid", referencedColumnName = "uuid"))
    public Set<Campaign> getCampaigns() {
        return campaigns;
    }

    public User setCampaigns(Set<Campaign> campaigns) {
        this.campaigns = campaigns;
        return this;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_uuid")
    public Set<Character> getCharacters() {
        return characters;
    }

    public User setCharacters(Set<Character> characters) {
        this.characters = characters;
        return this;
    }
}
