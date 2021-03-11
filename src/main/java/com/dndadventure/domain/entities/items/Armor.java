package com.dndadventure.domain.entities.items;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "armors")
public class Armor extends Item {
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
}
