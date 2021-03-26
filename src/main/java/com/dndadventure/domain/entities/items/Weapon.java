package com.dndadventure.domain.entities.items;

import com.dndadventure.domain.entities.constants.CharacterStatsEnum;
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
    private DiceTypeEnum damageDice;
    private CharacterStatsEnum damageModifier;
    private Byte hitChanceBonus;

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
    public DiceTypeEnum getDamageDice() {
        return damageDice;
    }

    public Weapon setDamageDice(DiceTypeEnum diceDamage) {
        this.damageDice = diceDamage;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public CharacterStatsEnum getDamageModifier() {
        return damageModifier;
    }

    public Weapon setDamageModifier(CharacterStatsEnum damageModifier) {
        this.damageModifier = damageModifier;
        return this;
    }

    public Byte getHitChanceBonus() {
        return hitChanceBonus;
    }

    public Weapon setHitChanceBonus(Byte hitChanceBonus) {
        this.hitChanceBonus = hitChanceBonus;
        return this;
    }
}
