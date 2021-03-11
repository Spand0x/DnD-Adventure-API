package com.dndadventure.domain.entities;

import com.dndadventure.domain.entities.constants.CharacterStatsEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Stats extends BaseEntity {
    private CharacterStatsEnum name;
    private Byte value;

    public Stats() {
    }

    @Enumerated(value = EnumType.STRING)
    public CharacterStatsEnum getName() {
        return name;
    }

    public Stats setName(CharacterStatsEnum name) {
        this.name = name;
        return this;
    }

    public Byte getValue() {
        return value;
    }

    public Stats setValue(Byte value) {
        this.value = value;
        return this;
    }
}
