package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.CharacterRaceCreateDto;
import com.dndadventure.domain.entities.CharacterRace;
import com.dndadventure.domain.entities.StatsModifier;
import com.dndadventure.exceptions.NotFoundException;
import com.dndadventure.repositories.CharacterRaceRepository;
import com.dndadventure.repositories.StatsModifierRepository;
import com.dndadventure.services.CharacterRaceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterRaceServiceImpl implements CharacterRaceService {
    private final CharacterRaceRepository characterRaceRepository;
    private final StatsModifierRepository modifierRepository;
    private final ModelMapper modelMapper;

    public CharacterRaceServiceImpl(CharacterRaceRepository characterRaceRepository,
                                    StatsModifierRepository modifierRepository,
                                    ModelMapper modelMapper) {
        this.characterRaceRepository = characterRaceRepository;
        this.modifierRepository = modifierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CharacterRace> findAll() {
        List<CharacterRace> all = this.characterRaceRepository.findAll();
        return all;
    }


    @Override
    public void create(CharacterRaceCreateDto characterRaceCreateDto) {
        CharacterRace characterRace = this.modelMapper.map(characterRaceCreateDto, CharacterRace.class);
        List<StatsModifier> modifiers = new ArrayList<>();
        characterRaceCreateDto.getModifiers().forEach(modif -> {
            StatsModifier statsModifier =
                this.modifierRepository.findById(modif.getUuid())
                    .orElseThrow(() -> new NotFoundException("Modifier not found"));
            modifiers.add(statsModifier);
        });
        characterRace.setModifiers(modifiers);
        this.characterRaceRepository.save(characterRace);
    }

    @Override
    public CharacterRace getById(String uuid) {
        return this.characterRaceRepository.findById(uuid)
            .orElseThrow(() -> new NotFoundException("Race not found."));
    }
}
