package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.constants.CharacterStatsEnum;
import com.dndadventure.domain.entities.constants.DiceTypeEnum;
import com.dndadventure.domain.entities.constants.RarityEnum;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WeaponCreateDto {
    private String uuid;
    private String name;
    private String description;
    private Integer gold;
    private RarityEnum rarity;
    private String weaponType;
    private String attackType;
    private DiceTypeEnum damageDice;
    private CharacterStatsEnum damageModifier;
    private Byte hitChanceBonus;
    private SpellNameDto spell;

    public WeaponCreateDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public WeaponCreateDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    @NotBlank(message = "Name cannot be empty.")
    public String getName() {
        return name;
    }

    public WeaponCreateDto setName(String name) {
        this.name = name;
        return this;
    }

    @NotBlank(message = "Description cannot be empty.")
    @Length(min = 5, message = "Description must contain at least 5 characters.")
    public String getDescription() {
        return description;
    }

    public WeaponCreateDto setDescription(String description) {
        this.description = description;
        return this;
    }

    @Min(value = 0, message = "Gold should be zero or positive number")
    public Integer getGold() {
        return gold;
    }

    public WeaponCreateDto setGold(Integer gold) {
        this.gold = gold;
        return this;
    }

    @NotNull(message = "Rarity cannot be empty.")
    public RarityEnum getRarity() {
        return rarity;
    }

    public WeaponCreateDto setRarity(RarityEnum rarity) {
        this.rarity = rarity;
        return this;
    }

    @NotBlank(message = "Weapon type cannot be empty.")
    public String getWeaponType() {
        return weaponType;
    }

    public WeaponCreateDto setWeaponType(String weaponType) {
        this.weaponType = weaponType;
        return this;
    }

    @NotBlank(message = "Attack type cannot be empty.")
    public String getAttackType() {
        return attackType;
    }

    public WeaponCreateDto setAttackType(String attackType) {
        this.attackType = attackType;
        return this;
    }

    @NotNull(message = "Damage dice cannot be empty.")
    public DiceTypeEnum getDamageDice() {
        return damageDice;
    }

    public WeaponCreateDto setDamageDice(DiceTypeEnum damageDice) {
        this.damageDice = damageDice;
        return this;
    }

    public CharacterStatsEnum getDamageModifier() {
        return damageModifier;
    }

    public WeaponCreateDto setDamageModifier(CharacterStatsEnum damageModifier) {
        this.damageModifier = damageModifier;
        return this;
    }

    public Byte getHitChanceBonus() {
        return hitChanceBonus;
    }

    public WeaponCreateDto setHitChanceBonus(Byte hitChanceBonus) {
        this.hitChanceBonus = hitChanceBonus;
        return this;
    }

    public SpellNameDto getSpell() {
        return spell;
    }

    public WeaponCreateDto setSpell(SpellNameDto spell) {
        this.spell = spell;
        return this;
    }
}
