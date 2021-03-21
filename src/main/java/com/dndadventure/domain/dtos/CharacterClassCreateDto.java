package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.constants.DiceTypeEnum;

public class CharacterClassCreateDto {
    private String name;
    private String description;
    private DiceTypeEnum hitPointsDice;

    public CharacterClassCreateDto() {
    }

    public String getName() {
        return name;
    }

    public CharacterClassCreateDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CharacterClassCreateDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public DiceTypeEnum getHitPointsDice() {
        return hitPointsDice;
    }

    public CharacterClassCreateDto setHitPointsDice(DiceTypeEnum hitPointsDice) {
        this.hitPointsDice = hitPointsDice;
        return this;
    }
}
