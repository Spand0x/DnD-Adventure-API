package com.dndadventure.domain.entities;

import com.dndadventure.domain.entities.constants.CharacterStatsEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class Stat extends BaseEntity {
    private CharacterStatsEnum name;
    private Byte value;

    public Stat() {
    }

    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Stats name cannot be empty.")
    public CharacterStatsEnum getName() {
        return name;
    }

    public Stat setName(CharacterStatsEnum name) {
        this.name = name;
        return this;
    }

    @NotNull(message = "Stats value cannot be empty.")
    public Byte getValue() {
        return value;
    }

    public Stat setValue(Byte value) {
        this.value = value;
        return this;
    }
}
