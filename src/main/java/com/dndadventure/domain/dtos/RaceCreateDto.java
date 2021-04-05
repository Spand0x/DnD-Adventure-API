package com.dndadventure.domain.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class RaceCreateDto {
    private String uuid;
    private String name;
    private String description;
    private List<RaceBaseTraitCreateDto> advantages;
    private List<RaceBaseTraitCreateDto> disadvantages;
    private List<StatModifierDto> modifiers;

    public RaceCreateDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public RaceCreateDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    @NotBlank(message = "Name cannot be empty.")
    @Length(min = 3, message = "Name must contain at least 3 characters.")
    public String getName() {
        return name;
    }

    public RaceCreateDto setName(String name) {
        this.name = name;
        return this;
    }

    @NotBlank(message = "Description cannot be empty.")
    @Length(min = 5, message = "Description must contain at least 5 characters.")
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

    public List<StatModifierDto> getModifiers() {
        return modifiers;
    }

    public RaceCreateDto setModifiers(List<StatModifierDto> modifiers) {
        this.modifiers = modifiers;
        return this;
    }
}
