package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.constants.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SpellCreateDto {
    private String uuid;
    private String name;
    private String description;
    private Byte level;
    private DiceTypeEnum damageDice;
    private CharacterStatsEnum damageModifier;
    private Byte hitChanceBonus;
    private String effect;
    private String notes;
    private String range;
    private SpellCastingTypeEnum castingType;
    private SpellDurationTypeEnum durationType;
    private Integer duration;
    private DurationUnitEnum durationUnit;

    public SpellCreateDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public SpellCreateDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    @NotBlank(message = "Name cannot be empty.")
    @Length(min = 3, message = "Name must contain at least 3 character")
    public String getName() {
        return name;
    }

    public SpellCreateDto setName(String name) {
        this.name = name;
        return this;
    }

    @NotBlank(message = "Description cannot be empty.")
    @Length(min = 5, message = "Name must contain at least 5 character")
    public String getDescription() {
        return description;
    }

    public SpellCreateDto setDescription(String description) {
        this.description = description;
        return this;
    }

    @Min(value = 1, message = "Level must be between 1 and 10")
    @Max(value = 10, message = "Level must be between 1 and 10")
    public Byte getLevel() {
        return level;
    }

    public SpellCreateDto setLevel(Byte level) {
        this.level = level;
        return this;
    }

    public DiceTypeEnum getDamageDice() {
        return damageDice;
    }

    public SpellCreateDto setDamageDice(DiceTypeEnum damageDice) {
        this.damageDice = damageDice;
        return this;
    }

    public CharacterStatsEnum getDamageModifier() {
        return damageModifier;
    }

    public SpellCreateDto setDamageModifier(CharacterStatsEnum damageModifier) {
        this.damageModifier = damageModifier;
        return this;
    }

    public Byte getHitChanceBonus() {
        return hitChanceBonus;
    }

    public SpellCreateDto setHitChanceBonus(Byte hitChanceBonus) {
        this.hitChanceBonus = hitChanceBonus;
        return this;
    }

    public String getEffect() {
        return effect;
    }

    public SpellCreateDto setEffect(String effect) {
        this.effect = effect;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public SpellCreateDto setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    @NotBlank(message = "Range cannot be empty.")
    public String getRange() {
        return range;
    }

    public SpellCreateDto setRange(String range) {
        this.range = range;
        return this;
    }

    @NotNull(message = "Casting type cannot be empty.")
    public SpellCastingTypeEnum getCastingType() {
        return castingType;
    }

    public SpellCreateDto setCastingType(SpellCastingTypeEnum castingType) {
        this.castingType = castingType;
        return this;
    }

    @NotNull(message = "Duration Type cannot be empty.")
    public SpellDurationTypeEnum getDurationType() {
        return durationType;
    }

    public SpellCreateDto setDurationType(SpellDurationTypeEnum durationType) {
        this.durationType = durationType;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public SpellCreateDto setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public DurationUnitEnum getDurationUnit() {
        return durationUnit;
    }

    public SpellCreateDto setDurationUnit(DurationUnitEnum durationUnit) {
        this.durationUnit = durationUnit;
        return this;
    }
}
