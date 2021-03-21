package com.dndadventure.services;

import com.dndadventure.domain.dtos.CharacterClassCreateDto;

public interface CharacterClassService {
    public void create(CharacterClassCreateDto characterClassCreateDto);
}
