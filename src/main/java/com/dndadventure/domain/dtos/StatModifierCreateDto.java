package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.constants.CharacterStatsEnum;

import javax.validation.constraints.NotNull;

public class StatModifierCreateDto {
    private CharacterStatsEnum name;
    private Byte value;

    public StatModifierCreateDto() {
    }

    @NotNull(message = "Stat modifier name cannot be empty.")
    public CharacterStatsEnum getName() {
        return name;
    }

    public StatModifierCreateDto setName(CharacterStatsEnum name) {
        this.name = name;
        return this;
    }

    @NotNull(message = "Stat modifier value cannot be empty.")
    public Byte getValue() {
        return value;
    }

    public StatModifierCreateDto setValue(Byte value) {
        this.value = value;
        return this;
    }
}
