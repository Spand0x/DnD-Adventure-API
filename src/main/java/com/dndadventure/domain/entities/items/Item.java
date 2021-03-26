package com.dndadventure.domain.entities.items;

import com.dndadventure.domain.entities.BaseEntity;
import com.dndadventure.domain.entities.Spell;
import com.dndadventure.domain.entities.constants.RarityEnum;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item extends BaseEntity {
    private String name;
    private String description;
    private Integer gold;
    private Integer quantity;
    private RarityEnum rarity;
    private Spell spell;

    //TODO: Add spell to Items

    public Item() {
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getGold() {
        return gold;
    }

    public Item setGold(Integer gold) {
        this.gold = gold;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Item setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public RarityEnum getRarity() {
        return rarity;
    }

    public Item setRarity(RarityEnum rarity) {
        this.rarity = rarity;
        return this;
    }

    @OneToOne
    public Spell getSpell() {
        return spell;
    }

    public Item setSpell(Spell spell) {
        this.spell = spell;
        return this;
    }
}

