package com.dndadventure.services;

import com.dndadventure.domain.dtos.SpellCreateDto;

public interface SpellService {
    void create (SpellCreateDto spellCreateDto);
}
