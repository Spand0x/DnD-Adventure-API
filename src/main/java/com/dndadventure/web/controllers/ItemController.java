package com.dndadventure.web.controllers;

import com.dndadventure.domain.dtos.WeaponCreateDto;
import com.dndadventure.services.ItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/weapon")
    @PreAuthorize("hasRole('DUNGEON_MASTER')")
    public void createWeapon(@RequestBody WeaponCreateDto weaponCreateDto){
        this.itemService.createWeapon(weaponCreateDto);
    }
}
