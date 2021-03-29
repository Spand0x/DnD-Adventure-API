package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.SpellCreateDto;
import com.dndadventure.domain.dtos.SpellDetailsDto;
import com.dndadventure.domain.dtos.SpellNameDto;
import com.dndadventure.domain.entities.Spell;
import com.dndadventure.exceptions.NotFoundException;
import com.dndadventure.repositories.SpellRepository;
import com.dndadventure.services.SpellService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        spell.setTemplate(true);
        this.spellRepository.save(spell);
    }

    @Override
    public List<SpellNameDto> getAllNames() {
        return this.spellRepository.getAllNames();
    }

    @Override
    public SpellDetailsDto getSpellDetails(String uuid) {
        Spell spell = this.spellRepository.findById(uuid)
            .orElseThrow(() -> new NotFoundException("Spell was not found"));
        return this.modelMapper.map(spell, SpellDetailsDto.class);
    }

    @Override
    public Spell getSpell(String uuid) {
        return this.spellRepository.findById(uuid)
            .orElseThrow(() -> new NotFoundException("Spell was not found"));
    }

    @Override
    public Page<SpellDetailsDto> getAllByPages(String searchValue, Pageable pageable) {
        String value = searchValue.toLowerCase().trim();
        Page<Spell> spells = this.spellRepository.findAllContainingValue(value, pageable);
        return spells.map(s -> this.modelMapper.map(s, SpellDetailsDto.class));
    }

    @Override
    public Set<Spell> getSpells(List<String> spellUuids) {
        Set<Spell> spells = new HashSet<>();
        spellUuids.forEach(uuid -> spells.add(this.spellRepository.findById(uuid)
            .orElseThrow(() -> new NotFoundException("Spell not found"))));
        return spells;
    }

}
