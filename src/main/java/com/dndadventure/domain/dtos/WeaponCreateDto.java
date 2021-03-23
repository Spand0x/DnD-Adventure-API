package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.constants.DiceTypeEnum;
import com.dndadventure.domain.entities.constants.RarityEnum;

public class WeaponCreateDto {
    private String name;
    private String description;
    private Integer gold;
    private Integer quantity;
    private RarityEnum rarity;
    private String weaponType;
    private String attackType;
    private DiceTypeEnum diceDamage;
    private Integer bonusDamage;

    public WeaponCreateDto() {
    }

    public String getName() {
        return name;
    }

    public WeaponCreateDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public WeaponCreateDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getGold() {
        return gold;
    }

    public WeaponCreateDto setGold(Integer gold) {
        this.gold = gold;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public WeaponCreateDto setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public RarityEnum getRarity() {
        return rarity;
    }

    public WeaponCreateDto setRarity(RarityEnum rarity) {
        this.rarity = rarity;
        return this;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public WeaponCreateDto setWeaponType(String weaponType) {
        this.weaponType = weaponType;
        return this;
    }

    public String getAttackType() {
        return attackType;
    }

    public WeaponCreateDto setAttackType(String attackType) {
        this.attackType = attackType;
        return this;
    }

    public DiceTypeEnum getDiceDamage() {
        return diceDamage;
    }

    public WeaponCreateDto setDiceDamage(DiceTypeEnum diceDamage) {
        this.diceDamage = diceDamage;
        return this;
    }

    public Integer getBonusDamage() {
        return bonusDamage;
    }

    public WeaponCreateDto setBonusDamage(Integer bonusDamage) {
        this.bonusDamage = bonusDamage;
        return this;
    }
}
