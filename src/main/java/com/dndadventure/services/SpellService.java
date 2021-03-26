package com.dndadventure.services;

import com.dndadventure.domain.dtos.SpellCreateDto;
import com.dndadventure.domain.dtos.SpellDetailsDto;
import com.dndadventure.domain.dtos.SpellNameDto;
import com.dndadventure.domain.entities.Spell;

import java.util.List;

public interface SpellService {
    void create (SpellCreateDto spellCreateDto);

    List<SpellNameDto> getAllNames();

    SpellDetailsDto getSpellDetails(String uuid);

    Spell getSpell(String uuid);
}
