package com.dndadventure.services;

import com.dndadventure.domain.dtos.SpellCreateDto;

public interface ActionService {

    void createSpell(SpellCreateDto spellCreateDto);
}
