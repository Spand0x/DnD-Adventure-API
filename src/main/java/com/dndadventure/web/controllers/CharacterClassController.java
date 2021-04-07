package com.dndadventure.web.controllers;

import com.dndadventure.domain.dtos.CharacterClassCreateDto;
import com.dndadventure.domain.dtos.CharacterClassDetailsDto;
import com.dndadventure.domain.entities.CharacterClass;
import com.dndadventure.services.CharacterClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<?> create(@RequestBody @Valid CharacterClassCreateDto characterClassCreateDto) {
        this.characterClassService.create(characterClassCreateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<CharacterClassDetailsDto> getAll() {
        return this.characterClassService.getAll();
    }

}
