package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.constants.CharacterStatsEnum;

public class StatsModifierCreateDto {
    private CharacterStatsEnum name;
    private Byte value;

    public StatsModifierCreateDto() {
    }

    public CharacterStatsEnum getName() {
        return name;
    }

    public StatsModifierCreateDto setName(CharacterStatsEnum name) {
        this.name = name;
        return this;
    }

    public Byte getValue() {
        return value;
    }

    public StatsModifierCreateDto setValue(Byte value) {
        this.value = value;
        return this;
    }
}
