package com.dndadventure.domain.dtos;

public class BaseRaceTraitsDetailsDto {
    private String name;
    private String description;

    public BaseRaceTraitsDetailsDto() {
    }

    public String getName() {
        return name;
    }

    public BaseRaceTraitsDetailsDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BaseRaceTraitsDetailsDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
