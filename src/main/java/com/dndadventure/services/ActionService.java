package com.dndadventure.services;

import com.dndadventure.domain.dtos.AttackCreateDto;
import com.dndadventure.domain.dtos.SpellCreateDto;
import com.dndadventure.domain.entities.Action;

import java.util.List;

public interface ActionService {

    void createSpell(SpellCreateDto spellCreateDto);

    void createAttack(AttackCreateDto attackCreateDto);

    List<Action> findAllAttack();
}
