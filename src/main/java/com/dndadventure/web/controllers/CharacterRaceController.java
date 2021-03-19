package com.dndadventure.web.controllers;

import com.dndadventure.domain.dtos.CharacterRaceCreateDto;
import com.dndadventure.domain.entities.CharacterRace;
import com.dndadventure.services.CharacterRaceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/races")
public class CharacterRaceController {

    private final CharacterRaceService characterRaceService;

    public CharacterRaceController(CharacterRaceService characterRaceService) {
        this.characterRaceService = characterRaceService;
    }

    @GetMapping
    public Iterable<CharacterRace> getAll() {
        return characterRaceService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('DUNGEON_MASTER')")
    public CharacterRace create(@RequestBody CharacterRaceCreateDto characterRaceCreateDto) {
        this.characterRaceService.save(characterRaceCreateDto);
        return null;
    }
}
