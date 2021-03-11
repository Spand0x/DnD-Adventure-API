package com.dndadventure.domain.entities.items;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "magic_items")
public class MagicItems extends Item {
    private Integer maxCharges;
    private Integer availableCharges;
    private boolean isActive;

    public MagicItems() {
    }

    public Integer getMaxCharges() {
        return maxCharges;
    }

    public MagicItems setMaxCharges(Integer maxCharges) {
        this.maxCharges = maxCharges;
        return this;
    }

    public Integer getAvailableCharges() {
        return availableCharges;
    }

    public MagicItems setAvailableCharges(Integer availableCharges) {
        this.availableCharges = availableCharges;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public MagicItems setActive(boolean active) {
        isActive = active;
        return this;
    }
}
