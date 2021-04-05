package com.dndadventure.web.controllers;

import com.dndadventure.domain.UuidDto;
import com.dndadventure.domain.dtos.CharacterCreateDto;
import com.dndadventure.domain.dtos.CharacterHpChangeDto;
import com.dndadventure.domain.dtos.CharacterViewDto;
import com.dndadventure.domain.entities.User;
import com.dndadventure.services.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> create(@RequestBody @Valid CharacterCreateDto characterCreateDto,
                                    @AuthenticationPrincipal User user) {
        this.characterService.create(characterCreateDto, user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CharacterViewDto> get(@PathVariable String uuid) {
        CharacterViewDto characterViewDto = this.characterService.get(uuid);
        return new ResponseEntity<>(characterViewDto, HttpStatus.OK);
    }

    @PostMapping("/change-hp")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> changeHp(@RequestBody CharacterHpChangeDto characterHpChangeDto) {
        this.characterService.changeHp(characterHpChangeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cast-spell")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> castSpell(@RequestBody UuidDto characterUuid) {
        this.characterService.castSpell(characterUuid.getUuid());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
