package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.RaceCreateDto;
import com.dndadventure.domain.entities.Race;
import com.dndadventure.domain.entities.StatsModifier;
import com.dndadventure.exceptions.NotFoundException;
import com.dndadventure.repositories.RaceRepository;
import com.dndadventure.repositories.StatsModifierRepository;
import com.dndadventure.services.RaceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RaceServiceImpl implements RaceService {
    private final RaceRepository raceRepository;
    private final StatsModifierRepository modifierRepository;
    private final ModelMapper modelMapper;

    public RaceServiceImpl(RaceRepository raceRepository,
                           StatsModifierRepository modifierRepository,
                           ModelMapper modelMapper) {
        this.raceRepository = raceRepository;
        this.modifierRepository = modifierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Race> findAll() {
        List<Race> all = this.raceRepository.findAll();
        return all;
    }


    @Override
    public void create(RaceCreateDto raceCreateDto) {
        Race race = this.modelMapper.map(raceCreateDto, Race.class);
        List<StatsModifier> modifiers = new ArrayList<>();
        raceCreateDto.getModifiers().forEach(modif -> {
            StatsModifier statsModifier =
                this.modifierRepository.findById(modif.getUuid())
                    .orElseThrow(() -> new NotFoundException("Modifier not found"));
            modifiers.add(statsModifier);
        });
        race.setModifiers(modifiers);
        this.raceRepository.save(race);
    }

    @Override
    public Race getById(String uuid) {
        return this.raceRepository.findByIdWithModifiers(uuid)
            .orElseThrow(() -> new NotFoundException("Race not found."));
    }
}
