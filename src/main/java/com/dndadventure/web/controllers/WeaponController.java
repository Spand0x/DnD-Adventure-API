package com.dndadventure.web.controllers;

import com.dndadventure.domain.dtos.WeaponCreateDto;
import com.dndadventure.domain.dtos.WeaponDetailsDto;
import com.dndadventure.services.WeaponService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/weapons")
public class WeaponController {
    private final WeaponService weaponService;

    public WeaponController(WeaponService weaponService) {
        this.weaponService = weaponService;
    }

    @PostMapping
    @PreAuthorize("hasRole('DUNGEON_MASTER')")
    public ResponseEntity<?> create(@RequestBody @Valid WeaponCreateDto weaponCreateDto) {
        this.weaponService.create(weaponCreateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public Page<WeaponDetailsDto> getAllByPages(@RequestParam(name = "search", defaultValue = "") String searchValue,
                                                Pageable pageable) {
        return this.weaponService.getAllByPages(searchValue, pageable);
    }

}
