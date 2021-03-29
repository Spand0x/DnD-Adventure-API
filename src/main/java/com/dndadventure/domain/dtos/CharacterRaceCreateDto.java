package com.dndadventure.domain.dtos;

import java.util.List;

public class CharacterRaceCreateDto {
    private String uuid;
    private String name;
    private String description;
    private List<RaceBaseTraitCreateDto> advantages;
    private List<RaceBaseTraitCreateDto> disadvantages;
    private List<StatsModifierDto> modifiers;

    public CharacterRaceCreateDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public CharacterRaceCreateDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public CharacterRaceCreateDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CharacterRaceCreateDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<RaceBaseTraitCreateDto> getAdvantages() {
        return advantages;
    }

    public CharacterRaceCreateDto setAdvantages(List<RaceBaseTraitCreateDto> advantages) {
        this.advantages = advantages;
        return this;
    }

    public List<RaceBaseTraitCreateDto> getDisadvantages() {
        return disadvantages;
    }

    public CharacterRaceCreateDto setDisadvantages(List<RaceBaseTraitCreateDto> disadvantages) {
        this.disadvantages = disadvantages;
        return this;
    }

    public List<StatsModifierDto> getModifiers() {
        return modifiers;
    }

    public CharacterRaceCreateDto setModifiers(List<StatsModifierDto> modifiers) {
        this.modifiers = modifiers;
        return this;
    }
}
