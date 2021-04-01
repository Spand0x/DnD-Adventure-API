package com.dndadventure.domain.dtos;

public class CharacterHpChangeDto {
    private String uuid;
    private Integer currentHitPoints;

    public CharacterHpChangeDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public CharacterHpChangeDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public Integer getCurrentHitPoints() {
        return currentHitPoints;
    }

    public CharacterHpChangeDto setCurrentHitPoints(Integer currentHitPoints) {
        this.currentHitPoints = currentHitPoints;
        return this;
    }
}
