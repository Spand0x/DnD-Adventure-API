package com.dndadventure.services;

import com.dndadventure.domain.dtos.RaceCreateDto;
import com.dndadventure.domain.dtos.RaceDetailsDto;
import com.dndadventure.domain.entities.Race;

import java.util.List;

public interface RaceService {
    void create(RaceCreateDto raceCreateDto);

    Race getById(String uuid);

    List<RaceDetailsDto> getAll();
}
