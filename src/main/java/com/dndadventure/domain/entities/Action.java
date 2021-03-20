package com.dndadventure.domain.entities;


import com.dndadventure.domain.entities.constants.ActionTypeEnum;
import com.dndadventure.domain.entities.constants.DiceTypeEnum;

import javax.persistence.*;

@Entity
@Table(name = "actions")
public class Action extends BaseEntity {
    private ActionTypeEnum type;
    private String name;
    private String description;
    private Integer range;
    private DiceTypeEnum damageDice;
    private Byte damageBonus;
    private Byte hitChanceBonus;
    private Byte availableCharges;
    private Byte maxCharges;
    private Byte unlockLevel;
    private String effect;
    private String notes;
    private String attackType;

    public Action() {
    }

    @Enumerated(EnumType.STRING)
    public ActionTypeEnum getType() {
        return type;
    }

    public Action setType(ActionTypeEnum type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public Action setName(String name) {
        this.name = name;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public Action setDescription(String description) {
        this.description = description;
        return this;
    }

    //Because 'range' is SQL keyword
    @Column(name = "action_range")
    public Integer getRange() {
        return range;
    }

    public Action setRange(Integer range) {
        this.range = range;
        return this;
    }

    public Byte getHitChanceBonus() {
        return hitChanceBonus;
    }

    public Action setHitChanceBonus(Byte bonus) {
        this.hitChanceBonus = bonus;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public DiceTypeEnum getDamageDice() {
        return damageDice;
    }

    public Action setDamageDice(DiceTypeEnum dice) {
        this.damageDice = dice;
        return this;
    }

    public Byte getDamageBonus() {
        return damageBonus;
    }

    public Action setDamageBonus(Byte damage) {
        this.damageBonus = damage;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public Action setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public Byte getMaxCharges() {
        return maxCharges;
    }

    public Action setMaxCharges(Byte maxCharges) {
        this.maxCharges = maxCharges;
        return this;
    }

    public Byte getAvailableCharges() {
        return availableCharges;
    }

    public Action setAvailableCharges(Byte availableCharges) {
        this.availableCharges = availableCharges;
        return this;
    }

    public Byte getUnlockLevel() {
        return unlockLevel;
    }

    public Action setUnlockLevel(Byte unlockLevel) {
        this.unlockLevel = unlockLevel;
        return this;
    }

    public String getEffect() {
        return effect;
    }

    public Action setEffect(String effect) {
        this.effect = effect;
        return this;
    }

    public String getAttackType() {
        return attackType;
    }

    public Action setAttackType(String attackType) {
        this.attackType = attackType;
        return this;
    }
}
