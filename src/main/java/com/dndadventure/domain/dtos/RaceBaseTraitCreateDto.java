package com.dndadventure.domain.dtos;

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

    public String getName() {
        return name;
    }

    public RaceBaseTraitCreateDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RaceBaseTraitCreateDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
