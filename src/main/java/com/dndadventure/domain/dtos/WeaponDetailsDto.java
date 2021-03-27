package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.constants.CharacterStatsEnum;
import com.dndadventure.domain.entities.constants.DiceTypeEnum;
import com.dndadventure.domain.entities.constants.RarityEnum;

public class WeaponDetailsDto {
    private String uuid;
    private String name;
    private String description;
    private Integer gold;
    private Integer quantity;
    private RarityEnum rarity;
    private String spell;
    private String weaponType;
    private String attackType;
    private DiceTypeEnum damageDice;
    private CharacterStatsEnum damageModifier;
    private Byte hitChanceBonus;

    public WeaponDetailsDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public WeaponDetailsDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public WeaponDetailsDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public WeaponDetailsDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getGold() {
        return gold;
    }

    public WeaponDetailsDto setGold(Integer gold) {
        this.gold = gold;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public WeaponDetailsDto setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public RarityEnum getRarity() {
        return rarity;
    }

    public WeaponDetailsDto setRarity(RarityEnum rarity) {
        this.rarity = rarity;
        return this;
    }

    public String getSpell() {
        return spell;
    }

    public WeaponDetailsDto setSpell(String spell) {
        this.spell = spell;
        return this;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public WeaponDetailsDto setWeaponType(String weaponType) {
        this.weaponType = weaponType;
        return this;
    }

    public String getAttackType() {
        return attackType;
    }

    public WeaponDetailsDto setAttackType(String attackType) {
        this.attackType = attackType;
        return this;
    }

    public DiceTypeEnum getDamageDice() {
        return damageDice;
    }

    public WeaponDetailsDto setDamageDice(DiceTypeEnum damageDice) {
        this.damageDice = damageDice;
        return this;
    }

    public CharacterStatsEnum getDamageModifier() {
        return damageModifier;
    }

    public WeaponDetailsDto setDamageModifier(CharacterStatsEnum damageModifier) {
        this.damageModifier = damageModifier;
        return this;
    }

    public Byte getHitChanceBonus() {
        return hitChanceBonus;
    }

    public WeaponDetailsDto setHitChanceBonus(Byte hitChanceBonus) {
        this.hitChanceBonus = hitChanceBonus;
        return this;
    }
}
