package com.dndadventure.web.controllers;

import com.dndadventure.domain.dtos.UserInfoDto;
import com.dndadventure.domain.dtos.UserRegisterDto;
import com.dndadventure.domain.entities.User;
import com.dndadventure.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    @PreAuthorize("hasRole('USER')")
    public UserInfoDto getInfo(@AuthenticationPrincipal User user) {
        return this.userService.get(user);
    }

    @PostMapping("/register")
    public void register(@RequestBody UserRegisterDto userRegisterDto) {
        this.userService.register(userRegisterDto);
    }
}
