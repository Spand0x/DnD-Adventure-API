package com.dndadventure.web.controllers;

import com.dndadventure.domain.dtos.StatsModifierCreateDto;
import com.dndadventure.domain.dtos.StatsModifierDto;
import com.dndadventure.domain.entities.StatsModifier;
import com.dndadventure.services.StatsModifierService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modifiers")
public class StatsModifierController {
    private final StatsModifierService modifierService;

    public StatsModifierController(StatsModifierService modifierService) {
        this.modifierService = modifierService;
    }

    @PostMapping
    @PreAuthorize("hasRole('DUNGEON_MASTER')")
    public StatsModifier create(@RequestBody StatsModifierCreateDto statsModifier) {
        return this.modifierService.save(statsModifier);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public List<StatsModifierDto> findAll() {
        return this.modifierService.findAll();
    }
}
