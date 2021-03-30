package com.dndadventure.web.controllers;

import com.dndadventure.domain.dtos.CharacterCreateDto;
import com.dndadventure.domain.dtos.CharacterViewDto;
import com.dndadventure.domain.entities.User;
import com.dndadventure.services.CharacterService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public void create(@RequestBody CharacterCreateDto characterCreateDto,
                       @AuthenticationPrincipal User user) {
        this.characterService.create(characterCreateDto, user);
    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasRole('USER')")
    public CharacterViewDto get(@PathVariable String uuid) {
        return this.characterService.get(uuid);
    }
}
