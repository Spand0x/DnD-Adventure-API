package com.dndadventure.domain.entities;

import com.dndadventure.domain.entities.constants.DiceTypeEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "dice_types")
public class DiceType extends BaseEntity {
    private DiceTypeEnum diceType;

    public DiceType() {
    }

    @Enumerated(value = EnumType.STRING)
    public DiceTypeEnum getDiceType() {
        return diceType;
    }

    public DiceType setDiceType(DiceTypeEnum diceType) {
        this.diceType = diceType;
        return this;
    }
}
