package com.dndadventure.services;

import com.dndadventure.domain.dtos.StatsModifierCreateDto;
import com.dndadventure.domain.dtos.StatsModifierDto;

import java.util.List;

public interface StatsModifierService {
    void create(StatsModifierCreateDto statsModifier);

    List<StatsModifierDto> findAll();
}
