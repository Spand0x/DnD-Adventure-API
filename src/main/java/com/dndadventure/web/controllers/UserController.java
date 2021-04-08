package com.dndadventure.web.controllers;

import com.dndadventure.domain.dtos.UserChangeRoleDto;
import com.dndadventure.domain.dtos.UserDetailsDto;
import com.dndadventure.domain.dtos.UserInfoDto;
import com.dndadventure.domain.dtos.UserRegisterDto;
import com.dndadventure.domain.entities.User;
import com.dndadventure.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public Page<UserDetailsDto> getAllByPages(@RequestParam(name = "search", defaultValue = "") String searchValue,
                                              Pageable pageable) {
        return this.userService.getAllByPages(searchValue, pageable);
    }

    @PostMapping("/change-role")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> changeRole(@RequestBody @Valid UserChangeRoleDto userChangeRoleDto) {
        this.userService.changeRole(userChangeRoleDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{uuid}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable String uuid) {
        this.userService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
