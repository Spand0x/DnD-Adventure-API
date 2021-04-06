package com.dndadventure.services;

import com.dndadventure.domain.dtos.StatModifierCreateDto;
import com.dndadventure.domain.dtos.StatModifierDto;

import java.util.List;

public interface StatModifierService {
    void create(StatModifierCreateDto statsModifier);

    List<StatModifierDto> findAll();
}
