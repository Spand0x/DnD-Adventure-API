package com.dndadventure.web.controllers;

import com.dndadventure.domain.dtos.UserInfoDto;
import com.dndadventure.domain.dtos.UserRegisterDto;
import com.dndadventure.domain.entities.User;
import com.dndadventure.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        this.userService.register(userRegisterDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
