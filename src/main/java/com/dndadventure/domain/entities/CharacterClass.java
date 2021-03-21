package com.dndadventure.domain.entities;

import com.dndadventure.domain.entities.constants.CharacterStatsEnum;
import com.dndadventure.domain.entities.constants.DiceTypeEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classes")
public class CharacterClass extends BaseEntity {
    private String name;
    private String description;
    private DiceTypeEnum hitPointsDice;
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
