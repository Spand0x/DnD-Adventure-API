package com.dndadventure.services;

import com.dndadventure.domain.dtos.StatModifierCreateDto;
import com.dndadventure.domain.dtos.StatModifierDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StatModifierService {
    void create(StatModifierCreateDto statsModifier);

    List<StatModifierDto> getAll();

    Page<StatModifierDto> getAllByPages(String searchValue, Pageable pageable);
}
