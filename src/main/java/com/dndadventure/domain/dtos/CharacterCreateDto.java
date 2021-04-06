package com.dndadventure.domain.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.List;

public class CharacterCreateDto {
    private String name;
    private String description;
    private Integer gold;
    private String race;
    private String clazz;
    private String imageUrl;
    private List<CharacterStatCreateDto> stats;
    private List<String> weapons;
    private List<String> spells;

    public CharacterCreateDto() {
    }

    @NotBlank(message = "Name cannot be empty.")
    @Length(min = 3, message = "Name must contain at least 3 characters.")
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

    @NotNull(message = "Gold should be zero or positive number.")
    @Min(value = 0, message = "Gold should be zero or positive number.")
    public Integer getGold() {
        return gold;
    }

    public CharacterCreateDto setGold(Integer gold) {
        this.gold = gold;
        return this;
    }

    @NotBlank(message = "Race cannot be empty.")
    public String getRace() {
        return race;
    }

    public CharacterCreateDto setRace(String race) {
        this.race = race;
        return this;
    }

    @NotBlank(message = "Class cannot be empty.")
    public String getClazz() {
        return clazz;
    }

    public CharacterCreateDto setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }

    @NotEmpty(message = "Stats cannot be empty.")
    @Size(min = 6, max = 6,message = "6 stats should be included.")
    public List<CharacterStatCreateDto> getStats() {
        return stats;
    }

    public CharacterCreateDto setStats(List<CharacterStatCreateDto> stats) {
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
