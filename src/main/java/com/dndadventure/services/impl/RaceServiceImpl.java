package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.RaceCreateDto;
import com.dndadventure.domain.dtos.RaceDetailsDto;
import com.dndadventure.domain.entities.Race;
import com.dndadventure.domain.entities.StatModifier;
import com.dndadventure.exceptions.NotFoundException;
import com.dndadventure.repositories.RaceRepository;
import com.dndadventure.repositories.StatModifierRepository;
import com.dndadventure.services.RaceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceServiceImpl implements RaceService {
    private final RaceRepository raceRepository;
    private final StatModifierRepository modifierRepository;
    private final ModelMapper modelMapper;

    public RaceServiceImpl(RaceRepository raceRepository,
                           StatModifierRepository modifierRepository,
                           ModelMapper modelMapper) {
        this.raceRepository = raceRepository;
        this.modifierRepository = modifierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(RaceCreateDto raceCreateDto) {
        Race race = this.modelMapper.map(raceCreateDto, Race.class);
        List<StatModifier> modifiers = new ArrayList<>();
        raceCreateDto.getModifiers().forEach(modif -> {
            StatModifier statModifier =
                this.modifierRepository.findById(modif.getUuid())
                    .orElseThrow(() -> new NotFoundException("Modifier not found"));
            modifiers.add(statModifier);
        });
        race.setModifiers(modifiers);
        this.raceRepository.save(race);
    }

    @Override
    public Race getById(String uuid) {
        return this.raceRepository.findByIdWithModifiers(uuid)
            .orElseThrow(() -> new NotFoundException("Race not found."));
    }

    @Override
    public List<RaceDetailsDto> getAll() {
        return this.raceRepository.findAll()
            .stream()
            .map(race -> this.modelMapper.map(race, RaceDetailsDto.class))
            .collect(Collectors.toList());
    }
}
