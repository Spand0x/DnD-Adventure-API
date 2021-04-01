package com.dndadventure.domain.dtos;

import com.dndadventure.domain.entities.*;
import com.dndadventure.domain.entities.items.Weapon;

import java.util.Set;

public class CharacterViewDto {
    private String uuid;
    private String name;
    private String description;
    private Byte level;
    private Integer armor;
    private Integer maxHitPoints;
    private Integer currentHitPoints;
    private Byte initiative;
    private Integer gold;
    private boolean isDead;
    private String imageUrl;
    private Race race;
    private CharacterClass clazz;
//    private Campaign campaign;
    private Set<CharacterStats> stats;
    private Set<Spell> spells;
//    private Set<Item> items;
    private Set<Weapon> weapons;
//    private Set<Armor> armors;
    private Double maxSpellCharges;
    private Double availableSpellCharges;

    public CharacterViewDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public CharacterViewDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public CharacterViewDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CharacterViewDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Byte getLevel() {
        return level;
    }

    public CharacterViewDto setLevel(Byte level) {
        this.level = level;
        return this;
    }

    public Integer getArmor() {
        return armor;
    }

    public CharacterViewDto setArmor(Integer armor) {
        this.armor = armor;
        return this;
    }

    public Integer getMaxHitPoints() {
        return maxHitPoints;
    }

    public CharacterViewDto setMaxHitPoints(Integer maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
        return this;
    }

    public Integer getCurrentHitPoints() {
        return currentHitPoints;
    }

    public CharacterViewDto setCurrentHitPoints(Integer currentHitPoints) {
        this.currentHitPoints = currentHitPoints;
        return this;
    }

    public Byte getInitiative() {
        return initiative;
    }

    public CharacterViewDto setInitiative(Byte initiative) {
        this.initiative = initiative;
        return this;
    }

    public Integer getGold() {
        return gold;
    }

    public CharacterViewDto setGold(Integer gold) {
        this.gold = gold;
        return this;
    }

    public boolean isDead() {
        return isDead;
    }

    public CharacterViewDto setDead(boolean dead) {
        isDead = dead;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CharacterViewDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Race getRace() {
        return race;
    }

    public CharacterViewDto setRace(Race race) {
        this.race = race;
        return this;
    }

    public CharacterClass getClazz() {
        return clazz;
    }

    public CharacterViewDto setClazz(CharacterClass clazz) {
        this.clazz = clazz;
        return this;
    }

    public Set<CharacterStats> getStats() {
        return stats;
    }

    public CharacterViewDto setStats(Set<CharacterStats> stats) {
        this.stats = stats;
        return this;
    }

    public Set<Spell> getSpells() {
        return spells;
    }

    public CharacterViewDto setSpells(Set<Spell> spells) {
        this.spells = spells;
        return this;
    }

    public Set<Weapon> getWeapons() {
        return weapons;
    }

    public CharacterViewDto setWeapons(Set<Weapon> weapons) {
        this.weapons = weapons;
        return this;
    }

    public Double getMaxSpellCharges() {
        return maxSpellCharges;
    }

    public CharacterViewDto setMaxSpellCharges(Double maxSpellCharges) {
        this.maxSpellCharges = maxSpellCharges;
        return this;
    }

    public Double getAvailableSpellCharges() {
        return availableSpellCharges;
    }

    public CharacterViewDto setAvailableSpellCharges(Double availableSpellCharges) {
        this.availableSpellCharges = availableSpellCharges;
        return this;
    }
}
