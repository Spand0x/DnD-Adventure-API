package com.dndadventure.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dndadventure.domain.entities.RefreshToken;
import com.dndadventure.domain.entities.User;
import com.dndadventure.domain.entities.UserPrincipal;
import com.dndadventure.exceptions.NotFoundException;
import com.dndadventure.repositories.RefreshTokenRepository;
import com.dndadventure.services.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {
    private static final String ACCESS_TOKEN_NAME = "access_token";
    private static final String REFRESH_TOKEN_NAME = "refresh_token";
    private static final int ACCESS_TOKEN_MAX_AGE_MINUTES = 15;
    private static final int REFRESH_TOKEN_MAX_AGE_MINUTES = 61;
    private static final int ONE_MINUTE_SECONDS = 60;
    private static final int ONE_MINUTE_MILLIS = 60_000;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${dndadventures.secret}")
    private String SECRET;
    @Value("${dndadventures.secure_token}")
    private boolean IS_SECURE;
    @Value("${dndadventures.token_http_only}")
    private boolean HTTP_ONLY;

    public AuthServiceImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public Cookie createAccessTokenCookie(UserPrincipal user) {
        final int expirationTime = ACCESS_TOKEN_MAX_AGE_MINUTES * ONE_MINUTE_MILLIS;
        String jwtAccessToken = JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
            .sign(Algorithm.HMAC512(SECRET.getBytes()));

        Cookie accessTokenCookie = new Cookie(ACCESS_TOKEN_NAME, jwtAccessToken);
        accessTokenCookie.setSecure(IS_SECURE);
        accessTokenCookie.setHttpOnly(HTTP_ONLY);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(ACCESS_TOKEN_MAX_AGE_MINUTES * ONE_MINUTE_SECONDS);
        return accessTokenCookie;
    }

    @Override
    public Cookie createRefreshTokenCookie(UserPrincipal user) {
        this.deleteUserRefreshTokens(user.getUser());
        RefreshToken refreshToken = this.createRefreshToken(user.getUser());

        Cookie refreshTokenCookie = new Cookie(REFRESH_TOKEN_NAME, refreshToken.getValue());
        refreshTokenCookie.setSecure(IS_SECURE);
        refreshTokenCookie.setHttpOnly(HTTP_ONLY);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(REFRESH_TOKEN_MAX_AGE_MINUTES * ONE_MINUTE_SECONDS);
        return refreshTokenCookie;
    }

    @Override
    public Optional<Cookie> getAccessTokenCookie(HttpServletRequest request) {
        if (request.getCookies() == null) return Optional.empty();
        return Arrays.stream(request.getCookies())
            .filter(cookie -> cookie.getName().equals(ACCESS_TOKEN_NAME))
            .findFirst();
    }

    @Override
    public Optional<Cookie> getRefreshTokenCookie(HttpServletRequest request) {
        return this.getCookieByName(request, REFRESH_TOKEN_NAME);
    }

    @Override
    public String getSubjectByAccessTokenCookie(Cookie accessTokenCookie) {
        return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
            .build()
            .verify(accessTokenCookie.getValue())
            .getSubject();
    }

    @Override
    public RefreshToken findByValue(String value) {
        RefreshToken refreshToken = this.refreshTokenRepository.findFirstByValue(value)
            .orElseThrow(() -> new NotFoundException("Refresh token not found."));

        if (LocalDateTime.now().isAfter(refreshToken.getExpiresAt())) {
            this.refreshTokenRepository.deleteById(refreshToken.getUuid());
            throw new NotFoundException("Expired refresh token.");
        }
        return refreshToken;
    }

    @Override
    public Iterable<Cookie> obtainNewTokens(RefreshToken refreshToken) {
        User user = refreshToken.getUser();
        UserPrincipal userPrincipal = new UserPrincipal(user);

        Cookie refreshTokenCookie = this.createRefreshTokenCookie(userPrincipal);
        Cookie accessTokenCookie = this.createAccessTokenCookie(userPrincipal);

        return List.of(accessTokenCookie, refreshTokenCookie);
    }

    private RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setValue(UUID.randomUUID().toString());
        refreshToken.setExpiresAt(LocalDateTime.now().plusMinutes(REFRESH_TOKEN_MAX_AGE_MINUTES));

        return this.refreshTokenRepository.save(refreshToken);
    }

    private Optional<Cookie> getCookieByName(HttpServletRequest request, String name) {
        if (request.getCookies() == null) return Optional.empty();
        return Arrays.stream(request.getCookies())
            .filter(cookie -> cookie.getName().equals(name))
            .findFirst();
    }

    private void deleteUserRefreshTokens(User user) {
        Iterable<RefreshToken> userRefreshTokens = this.refreshTokenRepository.findAllByUser(user);
        for (RefreshToken refreshToken : userRefreshTokens) {
            this.refreshTokenRepository.deleteById(refreshToken.getUuid());
        }
    }
}
