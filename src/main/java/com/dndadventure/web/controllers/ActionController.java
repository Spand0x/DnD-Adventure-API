package com.dndadventure.web.controllers;

import com.dndadventure.domain.dtos.AttackCreateDto;
import com.dndadventure.domain.dtos.SpellCreateDto;
import com.dndadventure.services.ActionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actions")
public class ActionController {

    private final ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @PostMapping("/spell")
    @PreAuthorize("hasRole('DUNGEON_MASTER')")
    public void createSpell(@RequestBody SpellCreateDto spellCreateDto) {
        this.actionService.createSpell(spellCreateDto);
    }

    @PostMapping("/attack")
    @PreAuthorize("hasRole('DUNGEON_MASTER')")
    public void createAttack(@RequestBody AttackCreateDto attackCreateDto) {
        this.actionService.createAttack(attackCreateDto);
    }
}
