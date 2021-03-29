package com.dndadventure.domain.entities.items;

import com.dndadventure.domain.entities.BaseEntity;
import com.dndadventure.domain.entities.Spell;
import com.dndadventure.domain.entities.constants.CharacterStatsEnum;
import com.dndadventure.domain.entities.constants.DiceTypeEnum;
import com.dndadventure.domain.entities.constants.RarityEnum;

import javax.persistence.*;

@Entity
@Table(name = "weapons")
public class Weapon extends BaseEntity {
    private String name;
    private String description;
    private Integer gold;
    private Integer quantity;
    private RarityEnum rarity;
    private Spell spell;
    private String weaponType;
    private String attackType;
    private DiceTypeEnum damageDice;
    private CharacterStatsEnum damageModifier;
    private Byte hitChanceBonus;
    private boolean template;

    public Weapon() {
    }

    public String getName() {
        return name;
    }

    public Weapon setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Weapon setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getGold() {
        return gold;
    }

    public Weapon setGold(Integer gold) {
        this.gold = gold;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Weapon setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public RarityEnum getRarity() {
        return rarity;
    }

    public Weapon setRarity(RarityEnum rarity) {
        this.rarity = rarity;
        return this;
    }

    @OneToOne(cascade = CascadeType.PERSIST)
    public Spell getSpell() {
        return spell;
    }

    public Weapon setSpell(Spell spell) {
        this.spell = spell;
        return this;
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

    public boolean isTemplate() {
        return template;
    }

    public Weapon setTemplate(boolean template) {
        this.template = template;
        return this;
    }
}
