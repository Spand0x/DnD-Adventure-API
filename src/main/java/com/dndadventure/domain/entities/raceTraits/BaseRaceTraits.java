package com.dndadventure.domain.entities.raceTraits;

import com.dndadventure.domain.entities.BaseEntity;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseRaceTraits extends BaseEntity {
    private String name;
    private String description;

    public BaseRaceTraits() {
    }

    public String getName() {
        return name;
    }

    public BaseRaceTraits setName(String name) {
        this.name = name;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public BaseRaceTraits setDescription(String description) {
        this.description = description;
        return this;
    }
}
