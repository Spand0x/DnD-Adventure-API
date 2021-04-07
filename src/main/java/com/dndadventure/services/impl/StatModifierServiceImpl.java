package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.StatModifierCreateDto;
import com.dndadventure.domain.dtos.StatModifierDto;
import com.dndadventure.domain.entities.StatModifier;
import com.dndadventure.repositories.StatModifierRepository;
import com.dndadventure.services.StatModifierService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatModifierServiceImpl implements StatModifierService {
    private final StatModifierRepository statModifierRepository;
    private final ModelMapper modelMapper;

    public StatModifierServiceImpl(StatModifierRepository statModifierRepository, ModelMapper modelMapper) {
        this.statModifierRepository = statModifierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(StatModifierCreateDto statsModifier) {
        if (this.statModifierRepository.findByNameAndValue(statsModifier.getName(), statsModifier.getValue()).isPresent()) {
            throw new IllegalArgumentException("This modifier already exists");
        }
        StatModifier modifier = this.modelMapper.map(statsModifier, StatModifier.class);
        this.statModifierRepository.save(modifier);
    }

    @Override
    public List<StatModifierDto> getAll() {
        return this.statModifierRepository.findAll()
            .stream()
            .map(mod -> this.modelMapper.map(mod, StatModifierDto.class))
            .collect(Collectors.toList());
    }
}
