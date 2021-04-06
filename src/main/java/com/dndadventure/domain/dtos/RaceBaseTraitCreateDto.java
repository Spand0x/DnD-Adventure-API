package com.dndadventure.domain.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class RaceBaseTraitCreateDto {
    private String uuid;
    private String name;
    private String description;

    public RaceBaseTraitCreateDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public RaceBaseTraitCreateDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    @NotBlank(message = "Name cannot be empty.")
    @Length(min = 3, message = "Description must contain at least 3 character.")
    public String getName() {
        return name;
    }

    public RaceBaseTraitCreateDto setName(String name) {
        this.name = name;
        return this;
    }

    @NotBlank(message = "Name cannot be empty.")
    @Length(min = 5, message = "Description must contain at least 5 character.")
    public String getDescription() {
        return description;
    }

    public RaceBaseTraitCreateDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
