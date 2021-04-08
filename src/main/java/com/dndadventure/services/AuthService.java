package com.dndadventure.services;

import com.dndadventure.domain.entities.RefreshToken;
import com.dndadventure.domain.entities.User;
import com.dndadventure.domain.entities.UserPrincipal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface AuthService {
    Cookie createAccessTokenCookie(UserPrincipal user);

    Cookie createRefreshTokenCookie(UserPrincipal user);

    Optional<Cookie> getAccessTokenCookie(HttpServletRequest request);

    Optional<Cookie> getRefreshTokenCookie(HttpServletRequest request);

    String getSubjectByAccessTokenCookie(Cookie accessTokenCookie);

    RefreshToken findByValue(String value);

    Iterable<Cookie> obtainNewTokens(RefreshToken refreshToken);

    void deleteUserRefreshTokens(User user);
}
