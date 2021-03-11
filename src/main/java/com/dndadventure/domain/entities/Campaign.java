package com.dndadventure.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "campaigns")
public class Campaign extends BaseEntity{
    private String name;
    private User creator;
    private Set<Character> characters;
    private String description;

    public Campaign() {
    }

    public String getName() {
        return name;
    }

    public Campaign setName(String name) {
        this.name = name;
        return this;
    }

    @ManyToOne
    public User getCreator() {
        return creator;
    }

    public Campaign setCreator(User creator) {
        this.creator = creator;
        return this;
    }

    @OneToMany(mappedBy = "campaign", orphanRemoval = true)
    public Set<Character> getCharacters() {
        return characters;
    }

    public Campaign setCharacters(Set<Character> characters) {
        this.characters = characters;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public Campaign setDescription(String description) {
        this.description = description;
        return this;
    }
}
