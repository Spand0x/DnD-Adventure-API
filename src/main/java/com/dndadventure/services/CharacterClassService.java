package com.dndadventure.services;

import com.dndadventure.domain.dtos.CharacterClassCreateDto;
import com.dndadventure.domain.entities.CharacterClass;

import java.util.List;

public interface CharacterClassService {
    void create(CharacterClassCreateDto characterClassCreateDto);

    List<CharacterClass> getAll();

    CharacterClass getById(String uuid);
}
