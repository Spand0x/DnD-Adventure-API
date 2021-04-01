package com.dndadventure.domain.entities;

import com.dndadventure.domain.entities.items.Armor;
import com.dndadventure.domain.entities.items.Item;
import com.dndadventure.domain.entities.items.Weapon;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "characters")
public class Character extends BaseEntity {
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
    private User user;
    private Campaign campaign;
    private Set<CharacterStats> stats;
    private Set<Spell> spells;
    private Set<Item> items;
    private Set<Weapon> weapons;
    private Set<Armor> armors;
    private Double maxSpellCharges;
    private Double availableSpellCharges;

    public Character() {
    }

    public String getName() {
        return name;
    }

    public Character setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    @Column(columnDefinition = "TEXT")
    public Character setDescription(String description) {
        this.description = description;
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
    public Race getRace() {
        return race;
    }

    public Character setRace(Race race) {
        this.race = race;
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
    public User getUser() {
        return user;
    }

    public Character setUser(User account) {
        this.user = account;
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "stats_uuid")
    public Set<CharacterStats> getStats() {
        return stats;
    }

    public Character setStats(Set<CharacterStats> stats) {
        this.stats = stats;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
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
    public Set<Item> getItems() {
        return items;
    }

    public Character setItems(Set<Item> items) {
        this.items = items;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "characters_weapons",
        joinColumns = @JoinColumn(name = "character_uuid", referencedColumnName = "uuid"),
        inverseJoinColumns = @JoinColumn(name = "weapon_uuid", referencedColumnName = "uuid"))
    public Set<Weapon> getWeapons() {
        return weapons;
    }

    public Character setWeapons(Set<Weapon> weapons) {
        this.weapons = weapons;
        return this;
    }

    @ManyToMany
    @JoinTable(
        name = "characters_armors",
        joinColumns = @JoinColumn(name = "character_uuid", referencedColumnName = "uuid"),
        inverseJoinColumns = @JoinColumn(name = "armor_uuid", referencedColumnName = "uuid"))
    public Set<Armor> getArmors() {
        return armors;
    }

    public Character setArmors(Set<Armor> armors) {
        this.armors = armors;
        return this;
    }

    public Double getMaxSpellCharges() {
        return maxSpellCharges;
    }

    public Character setMaxSpellCharges(Double maxSpellCharges) {
        this.maxSpellCharges = maxSpellCharges;
        return this;
    }

    public Double getAvailableSpellCharges() {
        return availableSpellCharges;
    }

    public Character setAvailableSpellCharges(Double availableSpellCharges) {
        this.availableSpellCharges = availableSpellCharges;
        return this;
    }
}
