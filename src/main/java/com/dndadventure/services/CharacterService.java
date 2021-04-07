package com.dndadventure.services;

import com.dndadventure.domain.dtos.CharacterCreateDto;
import com.dndadventure.domain.dtos.CharacterHpChangeDto;
import com.dndadventure.domain.dtos.CharacterViewDto;
import com.dndadventure.domain.entities.User;

public interface CharacterService {
    void create(CharacterCreateDto characterCreateDto, User user);

    CharacterViewDto get(String uuid);

    CharacterViewDto changeHp(CharacterHpChangeDto characterHpChangeDto);

    CharacterViewDto castSpell(String characterUuid);
}
