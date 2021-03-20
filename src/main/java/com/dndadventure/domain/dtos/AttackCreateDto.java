package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.constants.ActionTypeEnum;
import com.dndadventure.domain.entities.constants.DiceTypeEnum;

public class AttackCreateDto {
    private ActionTypeEnum type;
    private String name;
    private String description;
    private Integer range;
    private DiceTypeEnum damageDice;
    private Byte damageBonus;
    private Byte hitChanceBonus;
    private Byte unlockLevel;
    private String notes;
    private String effect;
    private String attackType;

    public AttackCreateDto() {
    }

    public ActionTypeEnum getType() {
        return type;
    }

    public AttackCreateDto setType(ActionTypeEnum type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public AttackCreateDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AttackCreateDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getRange() {
        return range;
    }

    public AttackCreateDto setRange(Integer range) {
        this.range = range;
        return this;
    }

    public DiceTypeEnum getDamageDice() {
        return damageDice;
    }

    public AttackCreateDto setDamageDice(DiceTypeEnum damageDice) {
        this.damageDice = damageDice;
        return this;
    }

    public Byte getDamageBonus() {
        return damageBonus;
    }

    public AttackCreateDto setDamageBonus(Byte damageBonus) {
        this.damageBonus = damageBonus;
        return this;
    }

    public Byte getHitChanceBonus() {
        return hitChanceBonus;
    }

    public AttackCreateDto setHitChanceBonus(Byte hitChanceBonus) {
        this.hitChanceBonus = hitChanceBonus;
        return this;
    }

    public Byte getUnlockLevel() {
        return unlockLevel;
    }

    public AttackCreateDto setUnlockLevel(Byte unlockLevel) {
        this.unlockLevel = unlockLevel;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public AttackCreateDto setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public String getEffect() {
        return effect;
    }

    public AttackCreateDto setEffect(String effect) {
        this.effect = effect;
        return this;
    }

    public String getAttackType() {
        return attackType;
    }

    public AttackCreateDto setAttackType(String attackType) {
        this.attackType = attackType;
        return this;
    }
}
