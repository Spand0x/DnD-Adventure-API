package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.SpellCreateDto;
import com.dndadventure.domain.entities.Spell;
import com.dndadventure.repositories.SpellRepository;
import com.dndadventure.services.SpellService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SpellServiceImpl implements SpellService {
    private final SpellRepository spellRepository;
    private final ModelMapper modelMapper;

    public SpellServiceImpl(SpellRepository spellRepository, ModelMapper modelMapper) {
        this.spellRepository = spellRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(SpellCreateDto spellCreateDto) {
        Spell spell = this.modelMapper.map(spellCreateDto, Spell.class);
        spell.setAvailableCharges(spellCreateDto.getMaxCharges());
        this.spellRepository.save(spell);
    }
}
