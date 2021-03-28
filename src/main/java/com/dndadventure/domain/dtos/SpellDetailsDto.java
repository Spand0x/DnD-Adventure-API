package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.constants.*;

public class SpellDetailsDto {
    private String uuid;
    private String name;
    private String description;
    private Byte level;
    private DiceTypeEnum damageDice;
    private CharacterStatsEnum damageModifier;
    private Byte hitChanceBonus;
    private Byte maxCharges;
    private String effect;
    private String notes;

    private String range;
    private SpellCastingTypeEnum castingType;

    private SpellDurationTypeEnum durationType;
    private Integer duration;
    private DurationUnitEnum durationUnit;

    public SpellDetailsDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public SpellDetailsDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public SpellDetailsDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SpellDetailsDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Byte getLevel() {
        return level;
    }

    public SpellDetailsDto setLevel(Byte level) {
        this.level = level;
        return this;
    }

    public DiceTypeEnum getDamageDice() {
        return damageDice;
    }

    public SpellDetailsDto setDamageDice(DiceTypeEnum damageDice) {
        this.damageDice = damageDice;
        return this;
    }

    public CharacterStatsEnum getDamageModifier() {
        return damageModifier;
    }

    public SpellDetailsDto setDamageModifier(CharacterStatsEnum damageModifier) {
        this.damageModifier = damageModifier;
        return this;
    }

    public Byte getHitChanceBonus() {
        return hitChanceBonus;
    }

    public SpellDetailsDto setHitChanceBonus(Byte hitChanceBonus) {
        this.hitChanceBonus = hitChanceBonus;
        return this;
    }

    public Byte getMaxCharges() {
        return maxCharges;
    }

    public SpellDetailsDto setMaxCharges(Byte maxCharges) {
        this.maxCharges = maxCharges;
        return this;
    }

    public String getEffect() {
        return effect;
    }

    public SpellDetailsDto setEffect(String effect) {
        this.effect = effect;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public SpellDetailsDto setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public String getRange() {
        return range;
    }

    public SpellDetailsDto setRange(String range) {
        this.range = range;
        return this;
    }

    public SpellCastingTypeEnum getCastingType() {
        return castingType;
    }

    public SpellDetailsDto setCastingType(SpellCastingTypeEnum castingType) {
        this.castingType = castingType;
        return this;
    }

    public SpellDurationTypeEnum getDurationType() {
        return durationType;
    }

    public SpellDetailsDto setDurationType(SpellDurationTypeEnum durationType) {
        this.durationType = durationType;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public SpellDetailsDto setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public DurationUnitEnum getDurationUnit() {
        return durationUnit;
    }

    public SpellDetailsDto setDurationUnit(DurationUnitEnum durationUnit) {
        this.durationUnit = durationUnit;
        return this;
    }
}
