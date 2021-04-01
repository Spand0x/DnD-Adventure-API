package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.CharacterClassCreateDto;
import com.dndadventure.domain.entities.CharacterClass;
import com.dndadventure.exceptions.NotFoundException;
import com.dndadventure.repositories.CharacterClassRepository;
import com.dndadventure.services.CharacterClassService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterClassServiceImpl implements CharacterClassService {
    private final CharacterClassRepository characterClassRepository;
    private final ModelMapper modelMapper;

    public CharacterClassServiceImpl(CharacterClassRepository characterClassRepository, ModelMapper modelMapper) {
        this.characterClassRepository = characterClassRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(CharacterClassCreateDto characterClassCreateDto) {
        CharacterClass characterClass = this.modelMapper.map(characterClassCreateDto, CharacterClass.class);
        if (characterClass.getMaxSpellCharges() == null) {
            characterClass.setMaxSpellCharges(0.0d)
                .setSpellChargesPerLevel(0.0d);
        }
        this.characterClassRepository.save(characterClass);
    }

    @Override
    public List<CharacterClass> getAll() {
        return this.characterClassRepository.findAll();
    }

    @Override
    public CharacterClass getById(String uuid) {
        return this.characterClassRepository.findById(uuid)
            .orElseThrow(() -> new NotFoundException("Character Class not found."));
    }
}
