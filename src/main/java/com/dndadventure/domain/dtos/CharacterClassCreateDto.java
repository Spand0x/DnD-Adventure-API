package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.constants.CharacterStatsEnum;
import com.dndadventure.domain.entities.constants.DiceTypeEnum;

public class CharacterClassCreateDto {
    private String uuid;
    private String name;
    private String description;
    private DiceTypeEnum hitPointsDice;
    private CharacterStatsEnum savingThrowStat;
    private Double maxSpellCharges;
    private Double spellChargesPerLevel;

    public CharacterClassCreateDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public CharacterClassCreateDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
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

    public CharacterStatsEnum getSavingThrowStat() {
        return savingThrowStat;
    }

    public CharacterClassCreateDto setSavingThrowStat(CharacterStatsEnum savingThrowStat) {
        this.savingThrowStat = savingThrowStat;
        return this;
    }

    public Double getMaxSpellCharges() {
        return maxSpellCharges;
    }

    public CharacterClassCreateDto setMaxSpellCharges(Double maxSpellCharges) {
        this.maxSpellCharges = maxSpellCharges;
        return this;
    }

    public Double getSpellChargesPerLevel() {
        return spellChargesPerLevel;
    }

    public CharacterClassCreateDto setSpellChargesPerLevel(Double spellChargesPerLevel) {
        this.spellChargesPerLevel = spellChargesPerLevel;
        return this;
    }
}
