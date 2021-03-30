package com.dndadventure.domain.dtos;

public class CharacterBaseInfoDto {
    private String uuid;
    private String name;
    private Byte level;
    private String clazzName;
    private String raceName;
    private String imageUrl;

    public CharacterBaseInfoDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public CharacterBaseInfoDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public CharacterBaseInfoDto setName(String name) {
        this.name = name;
        return this;
    }

    public Byte getLevel() {
        return level;
    }

    public CharacterBaseInfoDto setLevel(Byte level) {
        this.level = level;
        return this;
    }

    public String getClazzName() {
        return clazzName;
    }

    public CharacterBaseInfoDto setClazzName(String clazzName) {
        this.clazzName = clazzName;
        return this;
    }

    public String getRaceName() {
        return raceName;
    }

    public CharacterBaseInfoDto setRaceName(String raceName) {
        this.raceName = raceName;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CharacterBaseInfoDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
