package com.dndadventure.web.controllers;

import com.dndadventure.domain.dtos.SpellCreateDto;
import com.dndadventure.domain.dtos.SpellDetailsDto;
import com.dndadventure.domain.dtos.SpellNameDto;
import com.dndadventure.services.SpellService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/names")
    @PreAuthorize("hasRole('DUNGEON_MASTER')")
    public List<SpellNameDto> getAllNames(){
        return this.spellService.getAllNames();
    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasRole('DUNGEON_MASTER')")
    public SpellDetailsDto getSpell(@PathVariable String uuid){
        return this.spellService.getSpellDetails(uuid);
    }
}
