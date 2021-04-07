package com.dndadventure.domain.dtos;

import java.util.Set;

public class RaceDetailsDto {
    private String uuid;
    private String name;
    private String description;
    private Set<BaseRaceTraitsDetailsDto> advantages;
    private Set<BaseRaceTraitsDetailsDto> disadvantages;
    private Set<StatModifierDto> modifiers;

    public RaceDetailsDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public RaceDetailsDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public RaceDetailsDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RaceDetailsDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<BaseRaceTraitsDetailsDto> getAdvantages() {
        return advantages;
    }

    public RaceDetailsDto setAdvantages(Set<BaseRaceTraitsDetailsDto> advantages) {
        this.advantages = advantages;
        return this;
    }

    public Set<BaseRaceTraitsDetailsDto> getDisadvantages() {
        return disadvantages;
    }

    public RaceDetailsDto setDisadvantages(Set<BaseRaceTraitsDetailsDto> disadvantages) {
        this.disadvantages = disadvantages;
        return this;
    }

    public Set<StatModifierDto> getModifiers() {
        return modifiers;
    }

    public RaceDetailsDto setModifiers(Set<StatModifierDto> modifiers) {
        this.modifiers = modifiers;
        return this;
    }
}
