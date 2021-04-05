package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.constants.CharacterStatsEnum;

import javax.validation.constraints.NotNull;

public class StatModifierDto {
    private String uuid;
    private CharacterStatsEnum name;
    private Byte value;

    public StatModifierDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public StatModifierDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    @NotNull(message = "Stat name cannot be empty")
    public CharacterStatsEnum getName() {
        return name;
    }

    public StatModifierDto setName(CharacterStatsEnum name) {
        this.name = name;
        return this;
    }

    @NotNull(message = "Stat value cannot be empty")
    public Byte getValue() {
        return value;
    }

    public StatModifierDto setValue(Byte value) {
        this.value = value;
        return this;
    }
}
