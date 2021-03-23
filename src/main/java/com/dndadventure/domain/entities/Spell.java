package com.dndadventure.domain.entities;

import com.dndadventure.domain.entities.constants.*;

import javax.persistence.*;

@Entity
@Table(name = "spells")
public class Spell extends BaseEntity {
    private String name;
    private String description;
    private Byte level;
    private DiceTypeEnum damageDice;
    private CharacterStatsEnum damageModifier;
    private Byte hitChanceBonus;
    private Byte availableCharges;
    private Byte maxCharges;
    private String effect;
    private String notes;

    //Self / Ranged 20ft
    private String range;
    private SpellCastingTypeEnum castingType;

    private SpellDurationTypeEnum durationType;
    private Integer duration;
    private DurationUnitEnum durationUnit;

    public Spell() {
    }

    public String getName() {
        return name;
    }

    public Spell setName(String name) {
        this.name = name;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public Spell setDescription(String description) {
        this.description = description;
        return this;
    }

    public Byte getLevel() {
        return level;
    }

    public Spell setLevel(Byte level) {
        this.level = level;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public DiceTypeEnum getDamageDice() {
        return damageDice;
    }

    public Spell setDamageDice(DiceTypeEnum damageDice) {
        this.damageDice = damageDice;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public CharacterStatsEnum getDamageModifier() {
        return damageModifier;
    }

    public Spell setDamageModifier(CharacterStatsEnum damageModifier) {
        this.damageModifier = damageModifier;
        return this;
    }

    public Byte getHitChanceBonus() {
        return hitChanceBonus;
    }

    public Spell setHitChanceBonus(Byte hitChanceBonus) {
        this.hitChanceBonus = hitChanceBonus;
        return this;
    }

    public Byte getAvailableCharges() {
        return availableCharges;
    }

    public Spell setAvailableCharges(Byte availableCharges) {
        this.availableCharges = availableCharges;
        return this;
    }

    public Byte getMaxCharges() {
        return maxCharges;
    }

    public Spell setMaxCharges(Byte maxCharges) {
        this.maxCharges = maxCharges;
        return this;
    }

    public String getEffect() {
        return effect;
    }

    public Spell setEffect(String effect) {
        this.effect = effect;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public Spell setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    //Range is a key word in SQL
    @Column(name = "spell_range")
    public String getRange() {
        return range;
    }

    public Spell setRange(String range) {
        this.range = range;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public SpellCastingTypeEnum getCastingType() {
        return castingType;
    }

    public Spell setCastingType(SpellCastingTypeEnum castingType) {
        this.castingType = castingType;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public SpellDurationTypeEnum getDurationType() {
        return durationType;
    }

    public Spell setDurationType(SpellDurationTypeEnum durationType) {
        this.durationType = durationType;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public Spell setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public DurationUnitEnum getDurationUnit() {
        return durationUnit;
    }

    public Spell setDurationUnit(DurationUnitEnum durationUnit) {
        this.durationUnit = durationUnit;
        return this;
    }
}
