package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.constants.CharacterStatsEnum;

public class CharacterStatsCreateDto {
    private CharacterStatsEnum name;
    private Byte value;

    public CharacterStatsCreateDto() {
    }

    public CharacterStatsEnum getName() {
        return name;
    }

    public CharacterStatsCreateDto setName(CharacterStatsEnum name) {
        this.name = name;
        return this;
    }

    public Byte getValue() {
        return value;
    }

    public CharacterStatsCreateDto setValue(Byte value) {
        this.value = value;
        return this;
    }
}
