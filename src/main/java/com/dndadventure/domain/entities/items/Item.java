package com.dndadventure.domain.entities.items;

import com.dndadventure.domain.entities.BaseEntity;
import com.dndadventure.domain.entities.Rarity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item extends BaseEntity {
    private String name;
    private String description;
    private Integer gold;
    private String imageUrl;
    private Integer quantity;
    private Rarity rarity;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public Item setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Item setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @ManyToOne
    public Rarity getRarity() {
        return rarity;
    }

    public Item setRarity(Rarity rarity) {
        this.rarity = rarity;
        return this;
    }
}

