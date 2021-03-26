package com.dndadventure.domain.dtos;

public class SpellNameDto {
    private String uuid;
    private String name;

    public SpellNameDto() {
    }

    public SpellNameDto(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public SpellNameDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public SpellNameDto setName(String name) {
        this.name = name;
        return this;
    }

}
