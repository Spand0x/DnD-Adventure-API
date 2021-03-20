package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.SpellCreateDto;
import com.dndadventure.domain.entities.Action;
import com.dndadventure.repositories.ActionRepository;
import com.dndadventure.services.ActionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ActionServiceImpl implements ActionService {
    private final ActionRepository actionRepository;
    private final ModelMapper modelMapper;

    public ActionServiceImpl(ActionRepository actionRepository, ModelMapper modelMapper) {
        this.actionRepository = actionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createSpell(SpellCreateDto spellCreateDto) {
        Action spell = this.modelMapper.map(spellCreateDto, Action.class);
        spell.setAvailableCharges(spellCreateDto.getMaxCharges());
        this.actionRepository.save(spell);
    }
}
