package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.StatsModifierCreateDto;
import com.dndadventure.domain.dtos.StatsModifierDto;
import com.dndadventure.domain.entities.StatsModifier;
import com.dndadventure.repositories.StatsModifierRepository;
import com.dndadventure.services.StatsModifierService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatsModifierServiceImpl implements StatsModifierService {
    private final StatsModifierRepository statsModifierRepository;
    private final ModelMapper modelMapper;

    public StatsModifierServiceImpl(StatsModifierRepository statsModifierRepository, ModelMapper modelMapper) {
        this.statsModifierRepository = statsModifierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(StatsModifierCreateDto statsModifier) {
        StatsModifier modifier = this.modelMapper.map(statsModifier, StatsModifier.class);
        this.statsModifierRepository.save(modifier);
    }

    @Override
    public List<StatsModifierDto> findAll() {
        return this.statsModifierRepository.findAll()
            .stream()
            .map(mod -> this.modelMapper.map(mod, StatsModifierDto.class))
            .collect(Collectors.toList());
    }
}
