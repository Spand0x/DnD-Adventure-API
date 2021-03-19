package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.constants.CharacterStatsEnum;

public class StatsModifierDto {
    private String uuid;
    private CharacterStatsEnum name;
    private Byte value;

    public StatsModifierDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public StatsModifierDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public CharacterStatsEnum getName() {
        return name;
    }

    public StatsModifierDto setName(CharacterStatsEnum name) {
        this.name = name;
        return this;
    }

    public Byte getValue() {
        return value;
    }

    public StatsModifierDto setValue(Byte value) {
        this.value = value;
        return this;
    }
}
