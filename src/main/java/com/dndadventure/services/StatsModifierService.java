package com.dndadventure.services;

import com.dndadventure.domain.dtos.StatsModifierCreateDto;
import com.dndadventure.domain.dtos.StatsModifierDto;
import com.dndadventure.domain.entities.StatsModifier;

import java.util.List;

public interface StatsModifierService {
    StatsModifier save(StatsModifierCreateDto statsModifier);

    List<StatsModifierDto> findAll();
}
