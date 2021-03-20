package com.dndadventure.domain.entities.items;

import com.dndadventure.domain.entities.constants.DiceTypeEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "weapons")
public class Weapon extends Item {
    private String weaponType;
    private String attackType;
    private DiceTypeEnum diceDamage;
    private Integer bonusDamage;

    public Weapon() {
    }

    public String getWeaponType() {
        return weaponType;
    }

    public Weapon setWeaponType(String weaponType) {
        this.weaponType = weaponType;
        return this;
    }

    public String getAttackType() {
        return attackType;
    }

    public Weapon setAttackType(String attackType) {
        this.attackType = attackType;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public DiceTypeEnum getDiceDamage() {
        return diceDamage;
    }

    public Weapon setDiceDamage(DiceTypeEnum diceDamage) {
        this.diceDamage = diceDamage;
        return this;
    }

    public Integer getBonusDamage() {
        return bonusDamage;
    }

    public Weapon setBonusDamage(Integer bonusDamage) {
        this.bonusDamage = bonusDamage;
        return this;
    }
}
