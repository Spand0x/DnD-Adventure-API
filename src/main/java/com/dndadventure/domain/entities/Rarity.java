package com.dndadventure.domain.entities;

import com.dndadventure.domain.entities.constants.RarityEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "rarities")
public class Rarity extends BaseEntity {
    private RarityEnum rarity;

    public Rarity() {
    }

    @Enumerated(value = EnumType.STRING)
    public RarityEnum getRarity() {
        return rarity;
    }

    public Rarity setRarity(RarityEnum rarity) {
        this.rarity = rarity;
        return this;
    }
}
