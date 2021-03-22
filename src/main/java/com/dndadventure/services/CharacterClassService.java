package com.dndadventure.services;

import com.dndadventure.domain.dtos.CharacterClassCreateDto;
import com.dndadventure.domain.entities.CharacterClass;

import java.util.List;

public interface CharacterClassService {
    public void create(CharacterClassCreateDto characterClassCreateDto);

    List<CharacterClass> getAll();
}
