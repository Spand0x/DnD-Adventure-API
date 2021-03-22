package com.dndadventure.web.controllers;

import com.dndadventure.domain.dtos.CharacterClassCreateDto;
import com.dndadventure.domain.entities.CharacterClass;
import com.dndadventure.services.CharacterClassService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class CharacterClassController {
    private final CharacterClassService characterClassService;

    public CharacterClassController(CharacterClassService characterClassService) {
        this.characterClassService = characterClassService;
    }

    @PostMapping
    @PreAuthorize("hasRole('DUNGEON_MASTER')")
    public void create(@RequestBody CharacterClassCreateDto characterClassCreateDto) {
        this.characterClassService.create(characterClassCreateDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<CharacterClass> getAll() {
        return this.characterClassService.getAll();
    }

}
