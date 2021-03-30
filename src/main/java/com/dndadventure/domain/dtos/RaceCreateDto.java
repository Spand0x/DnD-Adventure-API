package com.dndadventure.domain.dtos;

import java.util.List;

public class RaceCreateDto {
    private String uuid;
    private String name;
    private String description;
    private List<RaceBaseTraitCreateDto> advantages;
    private List<RaceBaseTraitCreateDto> disadvantages;
    private List<StatsModifierDto> modifiers;

    public RaceCreateDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public RaceCreateDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public RaceCreateDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RaceCreateDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<RaceBaseTraitCreateDto> getAdvantages() {
        return advantages;
    }

    public RaceCreateDto setAdvantages(List<RaceBaseTraitCreateDto> advantages) {
        this.advantages = advantages;
        return this;
    }

    public List<RaceBaseTraitCreateDto> getDisadvantages() {
        return disadvantages;
    }

    public RaceCreateDto setDisadvantages(List<RaceBaseTraitCreateDto> disadvantages) {
        this.disadvantages = disadvantages;
        return this;
    }

    public List<StatsModifierDto> getModifiers() {
        return modifiers;
    }

    public RaceCreateDto setModifiers(List<StatsModifierDto> modifiers) {
        this.modifiers = modifiers;
        return this;
    }
}
