package com.dndadventure.web.controllers;

import com.dndadventure.domain.dtos.UserRoleDetails;
import com.dndadventure.services.UserRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class UserRoleController {
    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public List<UserRoleDetails> getAll() {
        return this.userRoleService.getAll();
    }
}
