package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.constants.CharacterStatsEnum;
import com.dndadventure.domain.entities.constants.DiceTypeEnum;

public class CharacterClassDetailsDto {
    private String uuid;
    private String name;
    private String description;
    private DiceTypeEnum hitPointsDice;
    private CharacterStatsEnum savingThrowStat;
    private Double maxSpellCharges;
    private Double spellChargesPerLevel;

    public CharacterClassDetailsDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public CharacterClassDetailsDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public CharacterClassDetailsDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CharacterClassDetailsDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public DiceTypeEnum getHitPointsDice() {
        return hitPointsDice;
    }

    public CharacterClassDetailsDto setHitPointsDice(DiceTypeEnum hitPointsDice) {
        this.hitPointsDice = hitPointsDice;
        return this;
    }

    public CharacterStatsEnum getSavingThrowStat() {
        return savingThrowStat;
    }

    public CharacterClassDetailsDto setSavingThrowStat(CharacterStatsEnum savingThrowStat) {
        this.savingThrowStat = savingThrowStat;
        return this;
    }

    public Double getMaxSpellCharges() {
        return maxSpellCharges;
    }

    public CharacterClassDetailsDto setMaxSpellCharges(Double maxSpellCharges) {
        this.maxSpellCharges = maxSpellCharges;
        return this;
    }

    public Double getSpellChargesPerLevel() {
        return spellChargesPerLevel;
    }

    public CharacterClassDetailsDto setSpellChargesPerLevel(Double spellChargesPerLevel) {
        this.spellChargesPerLevel = spellChargesPerLevel;
        return this;
    }
}
