package com.dndadventure.web.controllers;

import com.dndadventure.domain.dtos.SpellCreateDto;
import com.dndadventure.services.SpellService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spells")
public class SpellController {
    private final SpellService spellService;

    public SpellController(SpellService spellService) {
        this.spellService = spellService;
    }

    @PostMapping
    @PreAuthorize("hasRole('DUNGEON_MASTER')")
    public void create(@RequestBody SpellCreateDto spellCreateDto){
        this.spellService.create(spellCreateDto);
    }
}
