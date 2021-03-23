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
    private CharacterStatsEnum primaryStat;
    private CharacterStatsEnum savingThrowStat;
//    private List<Action> actions;

    public CharacterClass() {
    }

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
    public DiceTypeEnum getHitPointsDice() {
        return hitPointsDice;
    }

    public CharacterClass setHitPointsDice(DiceTypeEnum hitPointsDice) {
        this.hitPointsDice = hitPointsDice;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public CharacterStatsEnum getPrimaryStat() {
        return primaryStat;
    }

    public CharacterClass setPrimaryStat(CharacterStatsEnum primaryStat) {
        this.primaryStat = primaryStat;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public CharacterStatsEnum getSavingThrowStat() {
        return savingThrowStat;
    }

    public CharacterClass setSavingThrowStat(CharacterStatsEnum savingThrowStat) {
        this.savingThrowStat = savingThrowStat;
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
