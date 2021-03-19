package com.dndadventure.domain.entities;


import javax.persistence.*;

@Entity
@Table(name = "actions")
public class Action extends BaseEntity {
    private ActionType type;
    private String name;
    private String description;
    private Integer range;
    private Integer bonus;
    private DiceType dice;
    private Byte damage;
    private String notes;
    private Integer maxCharges;
    private Integer availableCharges;
    private Byte unlockLevel;

    public Action() {
    }

    @ManyToOne
    public ActionType getType() {
        return type;
    }

    public Action setType(ActionType type) {
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

    public Integer getBonus() {
        return bonus;
    }

    public Action setBonus(Integer bonus) {
        this.bonus = bonus;
        return this;
    }

    @OneToOne
    public DiceType getDice() {
        return dice;
    }

    public Action setDice(DiceType dice) {
        this.dice = dice;
        return this;
    }

    public Byte getDamage() {
        return damage;
    }

    public Action setDamage(Byte damage) {
        this.damage = damage;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public Action setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public Integer getMaxCharges() {
        return maxCharges;
    }

    public Action setMaxCharges(Integer maxCharges) {
        this.maxCharges = maxCharges;
        return this;
    }

    public Integer getAvailableCharges() {
        return availableCharges;
    }

    public Action setAvailableCharges(Integer availableCharges) {
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
}
