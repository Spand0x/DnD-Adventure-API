package com.dndadventure.domain.entities.items;

import com.dndadventure.domain.entities.BaseEntity;
import com.dndadventure.domain.entities.Spell;
import com.dndadventure.domain.entities.constants.RarityEnum;

import javax.persistence.*;

@Entity
@Table(name = "armors")
public class Armor extends BaseEntity {
    private String name;
    private String description;
    private Integer gold;
    private Integer quantity;
    private RarityEnum rarity;
    private Spell spell;
    private Integer armor;
    private String type;

    public Armor() {
    }

    public Integer getArmor() {
        return armor;
    }

    public Armor setArmor(Integer armor) {
        this.armor = armor;
        return this;
    }

    public String getType() {
        return type;
    }

    public Armor setType(String type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public Armor setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Armor setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getGold() {
        return gold;
    }

    public Armor setGold(Integer gold) {
        this.gold = gold;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Armor setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public RarityEnum getRarity() {
        return rarity;
    }

    public Armor setRarity(RarityEnum rarity) {
        this.rarity = rarity;
        return this;
    }

    @OneToOne
    public Spell getSpell() {
        return spell;
    }

    public Armor setSpell(Spell spell) {
        this.spell = spell;
        return this;
    }
}
