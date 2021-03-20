package com.dndadventure.services;

import com.dndadventure.domain.dtos.AttackCreateDto;
import com.dndadventure.domain.dtos.SpellCreateDto;

public interface ActionService {

    void createSpell(SpellCreateDto spellCreateDto);

    void createAttack(AttackCreateDto attackCreateDto);
}
