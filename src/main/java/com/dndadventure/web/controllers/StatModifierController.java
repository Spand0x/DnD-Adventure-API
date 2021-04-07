package com.dndadventure.web.controllers;

import com.dndadventure.domain.dtos.StatModifierCreateDto;
import com.dndadventure.domain.dtos.StatModifierDto;
import com.dndadventure.services.StatModifierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/modifiers")
public class StatModifierController {
    private final StatModifierService modifierService;

    public StatModifierController(StatModifierService modifierService) {
        this.modifierService = modifierService;
    }

    @PostMapping
    @PreAuthorize("hasRole('DUNGEON_MASTER')")
    public ResponseEntity<?> create(@RequestBody @Valid StatModifierCreateDto statsModifier) {
        this.modifierService.create(statsModifier);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public List<StatModifierDto> findAll() {
        return this.modifierService.getAll();
    }
}
