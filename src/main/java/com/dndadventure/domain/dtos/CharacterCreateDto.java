package com.dndadventure.domain.dtos;

import java.util.List;

public class CharacterCreateDto {
    private String name;
    private String description;
    private Integer gold;
    private String race;
    private String clazz;
    private String imageUrl;
    private List<CharacterStatsCreateDto> stats;
    private List<String> weapons;
    private List<String> spells;

    public CharacterCreateDto() {
    }

    public String getName() {
        return name;
    }

    public CharacterCreateDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CharacterCreateDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getGold() {
        return gold;
    }

    public CharacterCreateDto setGold(Integer gold) {
        this.gold = gold;
        return this;
    }

    public String getRace() {
        return race;
    }

    public CharacterCreateDto setRace(String race) {
        this.race = race;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public CharacterCreateDto setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }

    public List<CharacterStatsCreateDto> getStats() {
        return stats;
    }

    public CharacterCreateDto setStats(List<CharacterStatsCreateDto> stats) {
        this.stats = stats;
        return this;
    }

    public List<String> getWeapons() {
        return weapons;
    }

    public CharacterCreateDto setWeapons(List<String> weapons) {
        this.weapons = weapons;
        return this;
    }

    public List<String> getSpells() {
        return spells;
    }

    public CharacterCreateDto setSpells(List<String> spells) {
        this.spells = spells;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CharacterCreateDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
