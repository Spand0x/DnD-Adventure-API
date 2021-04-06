package com.dndadventure.web.controllers;

import com.dndadventure.domain.entities.RefreshToken;
import com.dndadventure.exceptions.NotFoundException;
import com.dndadventure.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/token")
    public ResponseEntity<?> obtainAccessToken(HttpServletRequest request,
                                               HttpServletResponse response) {
        Cookie refreshTokenCookie = this.authService.getRefreshTokenCookie(request)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No refresh token."));

        RefreshToken refreshToken;

        try {
            refreshToken = this.authService.findByValue(refreshTokenCookie.getValue());
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        this.authService.obtainNewTokens(refreshToken)
            .forEach(response::addCookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
