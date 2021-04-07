package com.dndadventure.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "race_traits")
public class BaseRaceTraits extends BaseEntity {
    private String name;
    private String description;

    public BaseRaceTraits() {
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public BaseRaceTraits setName(String name) {
        this.name = name;
        return this;
    }

    @Column(columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public BaseRaceTraits setDescription(String description) {
        this.description = description;
        return this;
    }
}
