package com.dndadventure.domain.entities;

import com.dndadventure.domain.entities.items.Item;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "characters")
public class Character extends BaseEntity {
    private String name;
    private Byte level;
    private Integer armor;
    private Integer maxHitPoints;
    private Integer currentHitPoints;
    private Byte initiative;
    private Integer gold;
    private boolean isDead;
    private String imageUrl;

    private CharacterRace characterRace;
    private CharacterClass clazz;

    private User account;
    private Campaign campaign;

    private Set<CharacterStats> stats;
    private Set<Spell> spells;

    private List<Item> items;

    //todo maybe add inventory

    public Character() {
    }

    public String getName() {
        return name;
    }

    public Character setName(String name) {
        this.name = name;
        return this;
    }

    public Byte getLevel() {
        return level;
    }

    public Character setLevel(Byte level) {
        this.level = level;
        return this;
    }

    public Integer getArmor() {
        return armor;
    }

    public Character setArmor(Integer armor) {
        this.armor = armor;
        return this;
    }

    public Integer getMaxHitPoints() {
        return maxHitPoints;
    }

    public Character setMaxHitPoints(Integer maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
        return this;
    }

    public Integer getCurrentHitPoints() {
        return currentHitPoints;
    }

    public Character setCurrentHitPoints(Integer currentHitPoints) {
        this.currentHitPoints = currentHitPoints;
        return this;
    }

    public Byte getInitiative() {
        return initiative;
    }

    public Character setInitiative(Byte initiative) {
        this.initiative = initiative;
        return this;
    }

    public boolean isDead() {
        return isDead;
    }

    public Character setDead(boolean dead) {
        isDead = dead;
        return this;
    }

    @OneToOne
    public CharacterRace getCharacterRace() {
        return characterRace;
    }

    public Character setCharacterRace(CharacterRace characterRace) {
        this.characterRace = characterRace;
        return this;
    }

    @OneToOne
    public CharacterClass getClazz() {
        return clazz;
    }

    public Character setClazz(CharacterClass clazz) {
        this.clazz = clazz;
        return this;
    }

    @ManyToOne
    public User getAccount() {
        return account;
    }

    public Character setAccount(User account) {
        this.account = account;
        return this;
    }

    @ManyToOne
    public Campaign getCampaign() {
        return campaign;
    }

    public Character setCampaign(Campaign campaign) {
        this.campaign = campaign;
        return this;
    }

    @OneToMany
    public Set<CharacterStats> getStats() {
        return stats;
    }

    public Character setStats(Set<CharacterStats> stats) {
        this.stats = stats;
        return this;
    }

    @ManyToMany
    @JoinTable(
        name = "characters_spells",
        joinColumns = @JoinColumn(name = "character_uuid", referencedColumnName = "uuid"),
        inverseJoinColumns = @JoinColumn(name = "spell_uuid", referencedColumnName = "uuid"))
    public Set<Spell> getSpells() {
        return spells;
    }

    public Character setSpells(Set<Spell> spells) {
        this.spells = spells;
        return this;
    }

    public Integer getGold() {
        return gold;
    }

    public Character setGold(Integer gold) {
        this.gold = gold;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Character setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @ManyToMany
    @JoinTable(
        name = "characters_items",
        joinColumns = @JoinColumn(name = "character_uuid", referencedColumnName = "uuid"),
        inverseJoinColumns = @JoinColumn(name = "item_uuid", referencedColumnName = "uuid"))
    public List<Item> getItems() {
        return items;
    }

    public Character setItems(List<Item> items) {
        this.items = items;
        return this;
    }
}
