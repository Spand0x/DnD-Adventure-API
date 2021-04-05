package com.dndadventure.domain.entities;

import com.dndadventure.domain.entities.constants.CharacterStatsEnum;
import com.dndadventure.domain.entities.constants.DiceTypeEnum;
import com.dndadventure.domain.entities.constants.RarityEnum;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "weapons")
public class Weapon extends BaseEntity {
    private String name;
    private String description;
    private Integer gold;
    private RarityEnum rarity;
    private Spell spell;
    private String weaponType;
    private String attackType;
    private DiceTypeEnum damageDice;
    private CharacterStatsEnum damageModifier;
    private Byte hitChanceBonus;

    public Weapon() {
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public Weapon setName(String name) {
        this.name = name;
        return this;
    }

    @Column(columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public Weapon setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(nullable = false)
    public Integer getGold() {
        return gold;
    }

    public Weapon setGold(Integer gold) {
        this.gold = gold;
        return this;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public RarityEnum getRarity() {
        return rarity;
    }

    public Weapon setRarity(RarityEnum rarity) {
        this.rarity = rarity;
        return this;
    }

    @OneToOne(cascade = {CascadeType.MERGE})
    public Spell getSpell() {
        return spell;
    }

    public Weapon setSpell(Spell spell) {
        this.spell = spell;
        return this;
    }

    @Column(nullable = false)
    public String getWeaponType() {
        return weaponType;
    }

    public Weapon setWeaponType(String weaponType) {
        this.weaponType = weaponType;
        return this;
    }

    @Column(nullable = false)
    public String getAttackType() {
        return attackType;
    }

    public Weapon setAttackType(String attackType) {
        this.attackType = attackType;
        return this;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
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
