package com.dndadventure.services;

import com.dndadventure.domain.dtos.RaceCreateDto;
import com.dndadventure.domain.entities.Race;

import java.util.List;

public interface RaceService {
    List<Race> findAll();
    
    void create(RaceCreateDto raceCreateDto);

    Race getById(String uuid);
}
