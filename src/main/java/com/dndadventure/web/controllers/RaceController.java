package com.dndadventure.web.controllers;

import com.dndadventure.domain.dtos.RaceCreateDto;
import com.dndadventure.domain.entities.Race;
import com.dndadventure.services.RaceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public void create(@RequestBody RaceCreateDto raceCreateDto) {
        this.raceService.create(raceCreateDto);
    }
}
