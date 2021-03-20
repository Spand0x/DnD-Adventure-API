package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.constants.ActionTypeEnum;
import com.dndadventure.domain.entities.constants.DiceTypeEnum;

public class SpellCreateDto {
    private ActionTypeEnum type;
    private String name;
    private String description;
    private Integer range;
    private DiceTypeEnum damageDice;
    private Byte damageBonus;
    private Byte hitChanceBonus;
    private Byte maxCharges;
    private Byte unlockLevel;
    private String effect;
    private String notes;

    public SpellCreateDto() {
    }

    public ActionTypeEnum getType() {
        return type;
    }

    public SpellCreateDto setType(ActionTypeEnum type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public SpellCreateDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SpellCreateDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getRange() {
        return range;
    }

    public SpellCreateDto setRange(Integer range) {
        this.range = range;
        return this;
    }

    public DiceTypeEnum getDamageDice() {
        return damageDice;
    }

    public SpellCreateDto setDamageDice(DiceTypeEnum damageDice) {
        this.damageDice = damageDice;
        return this;
    }

    public Byte getDamageBonus() {
        return damageBonus;
    }

    public SpellCreateDto setDamageBonus(Byte damageBonus) {
        this.damageBonus = damageBonus;
        return this;
    }

    public Byte getHitChanceBonus() {
        return hitChanceBonus;
    }

    public SpellCreateDto setHitChanceBonus(Byte hitChanceBonus) {
        this.hitChanceBonus = hitChanceBonus;
        return this;
    }

    public Byte getMaxCharges() {
        return maxCharges;
    }

    public SpellCreateDto setMaxCharges(Byte maxCharges) {
        this.maxCharges = maxCharges;
        return this;
    }

    public Byte getUnlockLevel() {
        return unlockLevel;
    }

    public SpellCreateDto setUnlockLevel(Byte unlockLevel) {
        this.unlockLevel = unlockLevel;
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
}
