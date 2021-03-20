package com.dndadventure.services;

import com.dndadventure.domain.dtos.CharacterRaceCreateDto;
import com.dndadventure.domain.entities.CharacterRace;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CharacterRaceService {
    List<CharacterRace> findAll();
    
    void create(CharacterRaceCreateDto characterRaceCreateDto);
}
