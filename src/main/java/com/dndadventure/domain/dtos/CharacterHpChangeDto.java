package com.dndadventure.domain.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CharacterHpChangeDto {
    private String uuid;
    private Integer currentHitPoints;

    public CharacterHpChangeDto() {
    }

    @NotBlank(message = "Id cannot be empty.")
    public String getUuid() {
        return uuid;
    }

    public CharacterHpChangeDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    @NotNull(message = "Current Hit Points cannot be empty")
    public Integer getCurrentHitPoints() {
        return currentHitPoints;
    }

    public CharacterHpChangeDto setCurrentHitPoints(Integer currentHitPoints) {
        this.currentHitPoints = currentHitPoints;
        return this;
    }
}
