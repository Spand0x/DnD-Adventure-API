package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.constants.CharacterStatsEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CharacterStatCreateDto {
    private CharacterStatsEnum name;
    private Byte value;

    public CharacterStatCreateDto() {
    }

    @NotEmpty(message = "Name cannot be empty.")
    public CharacterStatsEnum getName() {
        return name;
    }

    public CharacterStatCreateDto setName(CharacterStatsEnum name) {
        this.name = name;
        return this;
    }

    @NotNull(message = "Stat value cannot be empty.")
    public Byte getValue() {
        return value;
    }

    public CharacterStatCreateDto setValue(Byte value) {
        this.value = value;
        return this;
    }
}
