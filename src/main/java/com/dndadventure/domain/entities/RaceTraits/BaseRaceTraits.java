package com.dndadventure.domain.entities.RaceTraits;

import com.dndadventure.domain.entities.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

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

    public String getDescription() {
        return description;
    }

    public BaseRaceTraits setDescription(String description) {
        this.description = description;
        return this;
    }
}
