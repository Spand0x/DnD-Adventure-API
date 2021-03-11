package com.dndadventure.domain.entities.items;

import com.dndadventure.domain.entities.DiceType;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "weapons")
public class Weapon extends Item {
    private String weaponType;
    private String attackType;
    private DiceType diceDamage;
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

    @OneToOne
    public DiceType getDiceDamage() {
        return diceDamage;
    }

    public Weapon setDiceDamage(DiceType diceDamage) {
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
