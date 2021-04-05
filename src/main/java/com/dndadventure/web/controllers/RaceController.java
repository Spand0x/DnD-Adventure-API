package com.dndadventure.web.controllers;

import com.dndadventure.domain.dtos.RaceCreateDto;
import com.dndadventure.domain.entities.Race;
import com.dndadventure.services.RaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/races")
public class RaceController {

    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping
    public Iterable<Race> getAll() {
        return raceService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('DUNGEON_MASTER')")
    public ResponseEntity<?> create(@RequestBody @Valid RaceCreateDto raceCreateDto) {
        this.raceService.create(raceCreateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
