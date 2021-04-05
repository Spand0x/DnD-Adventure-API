package com.dndadventure.domain.entities;

import com.dndadventure.domain.entities.constants.CharacterStatsEnum;
import com.dndadventure.domain.entities.constants.DiceTypeEnum;

import javax.persistence.*;

@Entity
@Table(name = "classes")
public class CharacterClass extends BaseEntity {
    private String name;
    private String description;
    private DiceTypeEnum hitPointsDice;
    private CharacterStatsEnum savingThrowStat;
    private Double maxSpellCharges;
    private Double spellChargesPerLevel;

    public CharacterClass() {
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public CharacterClass setName(String name) {
        this.name = name;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public CharacterClass setDescription(String description) {
        this.description = description;
        return this;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public DiceTypeEnum getHitPointsDice() {
        return hitPointsDice;
    }

    public CharacterClass setHitPointsDice(DiceTypeEnum hitPointsDice) {
        this.hitPointsDice = hitPointsDice;
        return this;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public CharacterStatsEnum getSavingThrowStat() {
        return savingThrowStat;
    }

    public CharacterClass setSavingThrowStat(CharacterStatsEnum savingThrowStat) {
        this.savingThrowStat = savingThrowStat;
        return this;
    }

    public Double getMaxSpellCharges() {
        return maxSpellCharges;
    }

    public CharacterClass setMaxSpellCharges(Double maxSpellCharges) {
        this.maxSpellCharges = maxSpellCharges;
        return this;
    }

    public Double getSpellChargesPerLevel() {
        return spellChargesPerLevel;
    }

    public CharacterClass setSpellChargesPerLevel(Double availableSpellCharges) {
        this.spellChargesPerLevel = availableSpellCharges;
        return this;
    }

    //    @ManyToMany
//    @JoinTable(
//        name = "classes_actions",
//        joinColumns = @JoinColumn(name = "class_uuid", referencedColumnName = "uuid"),
//        inverseJoinColumns = @JoinColumn(name = "action_uuid",referencedColumnName = "uuid")
//    )
//    public List<Action> getActions() {
//        return actions;
//    }
//
//    public CharacterClass setActions(List<Action> spells) {
//        this.actions = spells;
//        return this;
//    }
}
//TODO
